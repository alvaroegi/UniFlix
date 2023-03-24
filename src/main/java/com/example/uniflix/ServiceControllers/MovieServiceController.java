package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Movie;
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

    //inicializar peliculas iniciales

    public Movie getMovie(long id) {
        return movies.get(id);
    }

    public LinkedList<Movie> getMovies() {
        LinkedList<Movie> m = new LinkedList<>();
        for(long i=1; i<=movies.size(); i++) {
            m.add(movies.get(i));
        }
        return m;
    }
    public long containsMovie(String name) {
        long i = 1;
        while(i<=lastId.longValue() && (movies.get(i)==null || !movies.get(i).getName().equals(name))) {
            i++;
        }
        if(i<=movies.size())
            return i;
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

}
