package com.example.uniflix.RestController;

import com.example.uniflix.Entities.Review;
import com.example.uniflix.ServiceControllers.MotyServiceController;
import com.example.uniflix.ServiceControllers.MovieServiceController;
import com.example.uniflix.ServiceControllers.ReviewServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ReviewRestController {
    @Autowired
    ReviewServiceController reviewService;
    @Autowired
    MovieServiceController moviesService;
    @Autowired
    MotyServiceController motyService;

    @GetMapping("/api/review")
    public Collection<Review> allReviewsApi() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/api/review/{id}")
    public ResponseEntity<Review> getReviewApi(@PathVariable long id) {
        Review r = reviewService.getRealReview(id);
        if (r != null) {
            return new ResponseEntity<>(r, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/review")
    @ResponseStatus(HttpStatus.CREATED)
    public Review addReviewApi(@RequestBody Review r, @RequestParam long Idmovie) {
        r.setMovie(moviesService.getRealMovie(Idmovie));
        r = reviewService.addReview(r);
        moviesService.updateScore(r.getMovie().getId());
        motyService.updateMotysOfCategorys(moviesService.getRealMovie(r.getMovie().getId()).getCategorys());
        return r;
    }

    @DeleteMapping("/api/review/{id}")
    public ResponseEntity<Review> deleteReviewApi(@PathVariable long id) {
        Review r = reviewService.deleteReview(id);
        if(r!=null) {
            moviesService.updateScore(r.getMovie().getId());
            motyService.updateMotysOfCategorys(moviesService.getMovie(r.getMovie().getId()).getCategorys());
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/review/{id}")
    public ResponseEntity<Review> updateReviewApi(@PathVariable long id, @RequestBody Review updatedReview) {
        Review r = reviewService.getRealReview(id);
        if (r != null) {
            updatedReview.setId(id);
            updatedReview = reviewService.updateReview(id, updatedReview);
            moviesService.updateScore(r.getMovie().getId());
            motyService.updateMotysOfCategorys(moviesService.getMovie(r.getMovie().getId()).getCategorys());
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
