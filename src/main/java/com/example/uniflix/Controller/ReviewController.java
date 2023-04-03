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

    @PostMapping("/newReview/{Idmovie}")
    public String newReview(Model model, @RequestParam String user, @RequestParam String comment, @RequestParam int score, @PathVariable long Idmovie) {
        Review r = new Review(user, comment, Idmovie, score);
        reviewService.addReview(r);
        moviesService.updateScore(Idmovie);
        Movie m = moviesService.getMovie(Idmovie);
        model.addAttribute("movie", m);
        if(!reviewService.getReviewsOfMovie(Idmovie).isEmpty())
            model.addAttribute("reviewList", reviewService.getReviewsOfMovie(Idmovie));
        return "info_movie";
    }

    @GetMapping("/createReview")
    public String review(Model model) {
        model.addAttribute("movies", moviesService.getMovies());
        return "reseñas_template";
    }

    @PostMapping("/modifyReview/{Idmovie}/{id}")
    public String updateReview(Model model, @PathVariable long id, @RequestParam String user, @RequestParam String comment, @RequestParam int score, @PathVariable long Idmovie){
        Review updatedReview = new Review(user, comment, Idmovie, score);
        updatedReview.setId(id);
        reviewService.updateReview(id, updatedReview);
        moviesService.updateScore(Idmovie);
        Movie m = moviesService.getMovie(Idmovie);
        model.addAttribute("movie", m);
        if(!reviewService.getReviewsOfMovie(Idmovie).isEmpty())
            model.addAttribute("reviewList", reviewService.getReviewsOfMovie(Idmovie));
        return "info_movie";
    }

    @PostMapping("/deleteReview/{Idmovie}/{id}")
    public String deleteReview(Model model, @PathVariable long id, @PathVariable long Idmovie, @RequestParam boolean confirmed) {
        if(confirmed)
            reviewService.deleteReview(id);
        moviesService.updateScore(Idmovie);
        Movie m = moviesService.getMovie(Idmovie);
        model.addAttribute("movie", m);
        if(!reviewService.getReviewsOfMovie(Idmovie).isEmpty())
            model.addAttribute("reviewList", reviewService.getReviewsOfMovie(Idmovie));
        return "info_movie";
    }

}
