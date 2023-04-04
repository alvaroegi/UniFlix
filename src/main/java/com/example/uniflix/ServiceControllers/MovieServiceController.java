package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Movie;
import com.example.uniflix.Entities.Review;
import com.example.uniflix.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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

    public Movie deleteMovie(long id){
        Movie m = movies.remove(id);
        reviewService.deleteReviewsofMovie(id);
        return m;
    }

    public void deleteWithoutCascade(long id,String director, int year,String synopsis){
        Movie aux = new Movie(movies.get(id));
        aux.setDirector(director);
        aux.setYear(year);
        aux.setSynopsis(synopsis);
        aux.setId(id);
        movies.put(id,aux);
    }

    public Collection<Movie> getAllMovies() {
        return movies.values();
    }

    public void updateMovie(long id, Movie m) {
        movies.put(id, m);
    }

    public LinkedList<Movie> searchMovies(String name){
        LinkedList<Movie> sol = new LinkedList<>();
        for(Map.Entry entry: movies.entrySet()) {
            Movie m = (Movie)entry.getValue();
            String aux = m.getName().toLowerCase().replace(" ","");
            name= name.toLowerCase().replace(" ","");
            if(aux.contains(name)) {
                sol.add(m);
            }
        }
        return sol;
    }

    public void updateScore(long id) {
        ArrayList<Review> reviewList = reviewService.getReviewsOfMovie(id);
        float amount = 0;
        float total = 0;
        for(Review r : reviewList) {
            amount++;
            total = total + r.getScore();
        }
        Movie m = movies.get(id);
        if(amount!=0)
            m.setScore((float)Math.round(total/amount*100)/100);
        else
            m.setScore(0);
        movies.put(id, m);
    }

    public boolean isCategory(Category c,Movie m) {
        boolean sol = false;
        String aux = c.getName().toLowerCase();
        ArrayList<Category> clist = m.getCategorys();
        for(Category caux : clist) {
            String s = caux.getName().toLowerCase();
            if(aux.equals(s)) sol=true;
        }
        return sol;
    }

}
