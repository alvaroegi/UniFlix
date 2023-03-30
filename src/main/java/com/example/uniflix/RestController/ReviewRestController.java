package com.example.uniflix.RestController;

import com.example.uniflix.Entities.Review;
import com.example.uniflix.ServiceControllers.ReviewServiceController;
import com.example.uniflix.ServiceControllers.UserServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ReviewRestController {
    @Autowired
    ReviewServiceController reviewService;

    @GetMapping("/review")
    public Collection<Review> todosAnuncios() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<Review> getUser(@PathVariable long id) {
        Review r = reviewService.getReview(id);
        if (r != null) {
            return new ResponseEntity<>(r, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/review")
    @ResponseStatus(HttpStatus.CREATED)
    public Review addUser(@RequestBody Review r) {
        reviewService.addReview(r);
        return r;
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<Review> deleteUser(@PathVariable long id) {
        Review r = reviewService.deleteReview(id);
        if(r!=null) {
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/review/{id}")
    public ResponseEntity<Review> updateUser(@PathVariable long id, @RequestBody Review updatedReview) {
        Review r = reviewService.getReview(id);
        if (r != null) {
            updatedReview.setId(id);
            reviewService.updateReview(id, updatedReview);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
