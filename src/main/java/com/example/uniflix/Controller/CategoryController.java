package com.example.uniflix.Controller;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Moty;
import com.example.uniflix.Entities.Movie;
import com.example.uniflix.ServiceControllers.CategoryServiceController;
import com.example.uniflix.ServiceControllers.MovieServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryServiceController categoryService;
    @Autowired
    MovieServiceController movieService;

    @GetMapping("/cat/{name}")
    public String cat(Model model, @PathVariable String name){
        Category c = categoryService.getCategory(name);
        List<Movie> mlist =  movieService.getMovies();
        ArrayList<Movie> m = new ArrayList<>();
        for(Movie aux : mlist) {
            if(movieService.isCategory(c,aux)) {
                m.add(aux);
            }
        }
        Moty motyInfo = categoryService.getMoty(c);
        ArrayList<Category> aux= new ArrayList<>();
        aux.add(c);
        Movie motyMovie = new Movie("Sin establecer","Sin establecer","Sin establecer",0,"default.jpg",aux);
        if(motyInfo.getScore()!=-1) {
            motyMovie = movieService.getMovie(motyInfo.getIdMovie());
        }
        model.addAttribute("category", c);
        model.addAttribute("movies",m);
        model.addAttribute("moty",motyMovie);
        return "info_category";
    }




}
