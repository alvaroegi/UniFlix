package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Movie;
import com.example.uniflix.Entities.Review;
import com.example.uniflix.InterfacesBBDD.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReviewServiceController {
    private Map<Long, Review> reviews = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();

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

    /* CASCADE
    public void deleteReviewsofMovie(long id) {
        for(Map.Entry entry: reviews.entrySet()) {
            Review r = (Review)entry.getValue();
            long aux = r.getMovie().getId();
            if(id==aux) {
                reviews.remove(r.getId());
            }
        }
    }
    */


    public Collection<Review> getAllReviews() {
        return reviewRepo.findAll();
    }

    public Review getReview(long id) {
        return reviewRepo.getReferenceById(id);
    }

    public Review deleteReview(long id) {
        Review r = reviewRepo.getReferenceById(id);
        reviewRepo.deleteById(id);
        return r;
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

        //long iden = r.getMovie().getId();
        //Movie m = moviesService.getMovie(iden);
        return reviewRepo.save(sol);
    }
}
