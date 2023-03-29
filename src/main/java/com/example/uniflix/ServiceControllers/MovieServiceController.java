package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Movie;
import com.example.uniflix.Entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MovieServiceController {
    private Map<Long, Movie> movies = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();

    @Autowired
    ReviewServiceController reviewService;

    //inicializar peliculas iniciales

    public Movie getMovie(long id) {
        return movies.get(id);
    }

    public LinkedList<Movie> getMovies() {
        LinkedList<Movie> movieList = new LinkedList<>();
        for(Map.Entry entry: movies.entrySet()) {
            Movie m = (Movie)entry.getValue();
                movieList.add(m);
        }
        return movieList;
    }
    public long containsMovie(String name) {
        long sol = 0;
        for(Map.Entry entry: movies.entrySet()) {
            Movie m = (Movie)entry.getValue();
            if(m.getName().toLowerCase().replace(" ", "").equals(name.toLowerCase().replace(" ", ""))) {
                sol = (long)entry.getKey();
            }
        }
        if(sol!=0)
            return sol;
        else
            return -1;
    }
    public Movie addMovie(Movie m){
        if(containsMovie(m.getName())!=-1)
            return null;
        else {
            long id = lastId.incrementAndGet();
            m.setId(id);
            movies.put(id, m);
            return m;
        }
    }

    public void deleteMovie(Movie m){
        movies.remove(m.getId());
        reviewService.deleteReviewsofMovie(m.getId());
    }

    public void deleteWithoutCascade(long id,String director, int year){
        Movie aux = new Movie(movies.get(id));
        movies.remove(id);
        aux.setDirector(director);
        aux.setYear(year);
        aux.setId(id);
        movies.put(id,aux);
    }

}
