package com.example.uniflix.Controller;

import com.example.uniflix.Entities.Movie;
import com.example.uniflix.Entities.Review;
import com.example.uniflix.ServiceControllers.MovieServiceController;
import com.example.uniflix.ServiceControllers.ReviewServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;

@Controller
public class ReviewController {
    @Autowired
    ReviewServiceController reviewService;
    @Autowired
    MovieServiceController moviesService;

    @PostMapping("/newReview")
    public String newReview(Model model, @RequestParam String user, @RequestParam String comment, @RequestParam int score, @RequestParam String movie) {
        Review r = new Review(user, comment, moviesService.containsMovie(movie), score);
        reviewService.addReview(r);
        return "reseñas_template";
    }

    @GetMapping("/create")
    public String review(Model model) {
        model.addAttribute("movies", moviesService.getMovies());
        return "reseñas_template";
    }

    @GetMapping("/modifyReview/{id}")
    public String updateReview(Model model, @PathVariable long id, @RequestParam String user, @RequestParam String comment, @RequestParam int score, @RequestParam String movie){
        Review r = new Review(user, comment, moviesService.containsMovie(movie), score);
        return "index";
    }

}
