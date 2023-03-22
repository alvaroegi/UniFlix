package com.example.uniflix.Controller;

import com.example.uniflix.Entities.Movie;
import com.example.uniflix.ServiceControllers.MovieServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MovieController {
    @Autowired
    MovieServiceController moviesService;
    @GetMapping("/peli/{name}")
    public String reseñas(Model model, @PathVariable String name){
        model.addAttribute("peli",name);
        return "reseñas_template";
    }
    @GetMapping("/movie")
    public String changeToMovie() { return "create_movie"; }
    /*@GetMapping("/")
    public String back(Model model) {
        model.addAttribute("movies", moviesService.getMovies());
        return "main";
    }*/
    @PostMapping("/result")                                                                                                /*para las imagenes-----------------------*/
    public String checkMovie(Model model, @RequestParam String name, @RequestParam String director, @RequestParam int year, @RequestParam/*("file")*/ MultipartFile image) {
        if(moviesService.addMovie(new Movie(name, director, year, image.getOriginalFilename()))!=null) {
            model.addAttribute("added", true);
            /* desde aqui para imagenes */
            // https://www.youtube.com/watch?v=BjHEuNdpC-U parte 1
            //https://www.youtube.com/watch?v=df67kmObW7M parte 2
            Path imagesDirectory = Paths.get("src/main/resources/static/images");
            String absolutePath = imagesDirectory.toFile().getAbsolutePath();
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
}
