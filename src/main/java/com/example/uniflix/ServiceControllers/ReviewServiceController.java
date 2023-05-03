package com.example.uniflix.ServiceControllers;


import com.example.uniflix.Entities.Review;
import com.example.uniflix.InterfacesBBDD.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewServiceController {
    @Autowired
    ReviewRepository reviewRepo;

    public Review addReview(Review newReview) {
        return reviewRepo.save(newReview);
    }

    public List<Review> getReviewsOfMovie(long idMovie) {
        List<Review> sol = new ArrayList<>();
        List<Review> reviewList = reviewRepo.findAll();
        for(Review r : reviewList) {
            long aux = r.getMovie().getId();
            if(idMovie==aux) {
                sol.add(r);
            }
        }
        return sol;
    }

    public Collection<Review> getAllReviews() {
        return reviewRepo.findAll();
    }


    public Review getRealReview(long id) {
        Optional<Review> aux = reviewRepo.findById(id);
        Review m = new Review();
        if(aux.isPresent()){
            m = aux.get();
        }
        return m;

    }

    public Review deleteReview(long id) {
        Optional<Review> aux = reviewRepo.findById(id);
        Review m = new Review();
        if(aux.isPresent()){
            m = aux.get();
        }
        reviewRepo.deleteById(id);
        return m;
    }

    public Review updateReview(long id, Review r) {
        Optional<Review> aux = reviewRepo.findById(id);
        Review sol = new Review();
        if(aux.isPresent()){
            sol = aux.get();
            sol.setName(r.getName());
            sol.setScore(r.getScore());
            sol.setComment(r.getComment());
        }
        return reviewRepo.save(sol);
    }
}
