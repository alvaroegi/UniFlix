package com.example.uniflix.Controller;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Movie;
import com.example.uniflix.ServiceControllers.CategoryServiceController;
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
import java.util.ArrayList;

@Controller
public class MovieController {
    @Autowired
    MovieServiceController moviesService;
    @Autowired
    ReviewServiceController reviewService;

    @Autowired
    CategoryServiceController categoryService;

    @PostConstruct
    public void init(){
        Movie y = new Movie("You","Greg Berlanti","Un joven profundamente obsesivo y peligrosamente seductor mueve cielo y tierra para instalarse en la vida de aquellas personas por quienes se siente cautivado",2018,"you.jpg");
        ArrayList<Category> you = new ArrayList<>();
        you.add(new Category("Suspense"));
        you.add(new Category("Drama"));
        y.setCategorys(you);
        moviesService.addMovie(y);
        Movie m = new Movie("The Mandalorian","Jon Favreau", "Serie de aventura espacial que se ubica en el universo de Stars Wars",2019,"mandalorian.jpg");
        ArrayList<Category> mand = new ArrayList<>();
        mand.add(new Category("Accion"));
        mand.add(new Category("Suspense"));
        m.setCategorys(mand);
        moviesService.addMovie(m);
        Movie t = new Movie("The Last of Us","Craig Mazin","Basado en un videojuego de accion y aventuras la serie nos relata como Joel y Ellie sobreviven a una pandemia en EEUU",2023,"thelastofus.jpg");
        ArrayList<Category> tlou = new ArrayList<>();
        tlou.add(new Category("Miedo"));
        tlou.add(new Category("Accion"));
        t.setCategorys(tlou);
        moviesService.addMovie(t);
        categoryService.addCategory(new Category("Miedo"));
        categoryService.addCategory(new Category("Accion"));
        categoryService.addCategory(new Category("Drama"));
        categoryService.addCategory(new Category("Anime"));
        categoryService.addCategory(new Category("Suspense"));
    }

    @GetMapping("/")
    public String back(Model model) {
        model.addAttribute("movies", moviesService.getMovies());
        model.addAttribute("categorys", categoryService.getCategorys());
        return "index";
    }

    @GetMapping("/peli/{name}")
    public String reseñas(Model model, @PathVariable String name){
        Movie m = moviesService.getMovie(moviesService.containsMovie(name));
        model.addAttribute("movie", m);
        model.addAttribute("categorys",categoryService.getCategorys());
        if(!reviewService.getReviewsOfMovie(moviesService.containsMovie(name)).isEmpty())
            model.addAttribute("reviewList", reviewService.getReviewsOfMovie(moviesService.containsMovie(name)));
        return "info_movie";
    }
    @GetMapping("/createMovie")
    public String changeToMovie() { return "create_movie"; }
    @PostMapping("/result")                                                                                                /*para las imagenes-----------------------*/
    public String checkMovie(Model model, @RequestParam String name, @RequestParam String director,@RequestParam String synopsis, @RequestParam int year, @RequestParam/*("file")*/ MultipartFile image) {
        if(moviesService.addMovie(new Movie(name, director,synopsis, year, image.getOriginalFilename()))!=null) {
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
    public String update_delete(Model model, @RequestParam String movie, @RequestParam boolean confirmed) {
        if(confirmed) {
            moviesService.deleteMovie(moviesService.containsMovie(movie));
            model.addAttribute("modified", true);
        }
        model.addAttribute("movies", moviesService.getMovies());
        return "update_movie";
    }

    @PostMapping("/modify_nondelete")
    public String update_nondelete(Model model, @RequestParam String movie, @RequestParam String director, @RequestParam int year,@RequestParam String synopsis) {
        long id = moviesService.containsMovie(movie);
        if(id!=-1) {
            moviesService.deleteWithoutCascade(id, director, year,synopsis);
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