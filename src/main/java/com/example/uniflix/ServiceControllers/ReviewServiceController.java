package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Movie;
import com.example.uniflix.Entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReviewServiceController {
    private Map<Long, Review> reviews = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();



    public Review addReview(Review newReview) {
        long id = lastId.incrementAndGet();
        newReview.setId(id);
        reviews.put(id,newReview);

        return newReview;
    }

    public List<Review> getReviewsOfMovie(long idMovie) {
        List<Review> sol = new ArrayList<>();
        List<Review> reviewList = reviewRepo.findAll();
        for(Review r : reviewList) {
            long aux = r.getMovie().getId();
            if(idMovie==aux) {
                reviewList.add(r);
            }
        }
        return reviewList;
    }

    public void deleteReviewsofMovie(long id) {
        for(Map.Entry entry: reviews.entrySet()) {
            Review r = (Review)entry.getValue();
            long aux = r.getMovie().getId();
            if(id==aux) {
                reviews.remove(r.getId());
            }
        }
    }

    public Collection<Review> getAllReviews() {
        return reviews.values();
    }

    public Review getReview(long id) {
        return reviews.get(id);
    }

    public Review deleteReview(long id) {
        Review r = reviews.remove(id);
        return r;
    }

    public void updateReview(long id, Review r) {
        long iden = r.getMovie().getId();
        //Movie m = moviesService.getMovie(iden);
        reviewRepo.save(sol);
    }
}
