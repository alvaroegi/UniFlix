package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
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
        //añadirlo al futuro array de pelicula
        return newReview;
    }

    public ArrayList<Review> getReviewsOfMovie(String movie) {
        ArrayList<Review> reviewList = new ArrayList<>();
        for(Map.Entry entry: reviews.entrySet()) {
            Review r = (Review)entry.getValue();
            movie = movie.toLowerCase();
            String aux = r.getMovie().getName().toLowerCase();
            if(aux.equals(movie)) {
                reviewList.add(r);
            }
        }
        return reviewList;
    }

    public void deleteReviewsofMovie(long id) {
        for(Map.Entry entry: reviews.entrySet()) {
            Review r = (Review)entry.getValue();
            long aux = r.getMovie();
            if(id==aux) {
                reviews.remove(r.getId());
            }
        }
    }
}
