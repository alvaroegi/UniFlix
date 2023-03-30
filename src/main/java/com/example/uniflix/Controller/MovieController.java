package com.example.uniflix.Controller;

import com.example.uniflix.Entities.Movie;
import com.example.uniflix.ServiceControllers.MovieServiceController;
import com.example.uniflix.ServiceControllers.ReviewServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MovieController {
    @Autowired
    MovieServiceController moviesService;
    @Autowired
    ReviewServiceController reviewService;

    @PostConstruct
    public void init(){
        moviesService.addMovie(new Movie("You","Greg Berlanti",2018,"you.jpg"));
        moviesService.addMovie(new Movie("The Mandalorian","Jon Favreau",2019,"mandalorian.jpg"));
        moviesService.addMovie(new Movie("The Last of Us","Craig Mazin",2023,"thelastofus.jpg"));
    }

    @GetMapping("/")
    public String back(Model model) {
        model.addAttribute("movies", moviesService.getMovies());
        return "index";
    }

    @GetMapping("/peli/{name}")
    public String reseñas(Model model, @PathVariable String name){
        Movie m = moviesService.getMovie(moviesService.containsMovie(name));
        model.addAttribute("movie", m);
        if(!reviewService.getReviewsOfMovie(moviesService.containsMovie(name)).isEmpty())
            model.addAttribute("reviewList", reviewService.getReviewsOfMovie(moviesService.containsMovie(name)));
        return "info_movie";
    }
    @GetMapping("/createMovie")
    public String changeToMovie() { return "create_movie"; }
    @PostMapping("/result")                                                                                                /*para las imagenes-----------------------*/
    public String checkMovie(Model model, @RequestParam String name, @RequestParam String director, @RequestParam int year, @RequestParam/*("file")*/ MultipartFile image) {
        if(moviesService.addMovie(new Movie(name, director, year, image.getOriginalFilename()))!=null) {
            model.addAttribute("added", true);
            /* desde aqui para imagenes */
            // https://www.youtube.com/watch?v=BjHEuNdpC-U parte 1
            // https://www.youtube.com/watch?v=df67kmObW7M parte 2
            // para solucionar lo de las imagenes => https://www.youtube.com/watch?v=vVCgPzjbh2s
            //Path imagesDirectory = Paths.get("src/main/resources/static/images");
            //String absolutePath = imagesDirectory.toFile().getAbsolutePath();
            String absolutePath = "C://Uniflix//Peliculas";
            try {
                byte[] bytesImage = image.getBytes();
                Path completePath = Paths.get(absolutePath + "/" + image.getOriginalFilename());
                Files.write(completePath, bytesImage);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            /* hasta aqui lo de las imagenes */
        }
        else
            model.addAttribute("notAdded", true);
        return "create_movie";
    }

    @GetMapping("/modify")
    public String update(Model model) {
        model.addAttribute("movies", moviesService.getMovies());
        return "update_movie";
    }
    @PostMapping("/modify_delete")
    public String update_delete(Model model, @RequestParam String movie) {
        moviesService.deleteMovie(moviesService.containsMovie(movie));
        model.addAttribute("modified", true);
        model.addAttribute("movies", moviesService.getMovies());
        return "update_movie";
    }

    @PostMapping("/modify_nondelete")
    public String update_nondelete(Model model, @RequestParam String movie, @RequestParam String director, @RequestParam int year) {
        long id = moviesService.containsMovie(movie);
        if(id!=-1) {
            moviesService.deleteWithoutCascade(id, director, year);
        }
        model.addAttribute("modified", true);
        model.addAttribute("movies", moviesService.getMovies());
        return "update_movie";
    }

    @GetMapping("/search")
    public String searchFilms(Model model, @RequestParam String searching) {
        model.addAttribute("filter", searching);
        model.addAttribute("moviesWithFilter", moviesService.searchMovies(searching));
        return "index";
    }

}