package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Review;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReviewServiceController {
    private Map<Long, Review> reviews = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();

    public Review addReview(String user, String comment, int score, String movie) {
        Review r = new Review(user,comment,movie,score);
        long id = lastId.incrementAndGet();
        r.setId(id);
        reviews.put(id,r);
        //añadirlo al futuro array de pelicula
        return r;
    }
}
