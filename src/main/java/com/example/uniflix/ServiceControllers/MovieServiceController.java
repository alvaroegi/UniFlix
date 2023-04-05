package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.*;
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
    @Autowired
    CategoryServiceController categoryService;

    @Autowired
    MotyServiceController motyService;
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
            categoryService.addMovieToCategories(m);
            long id = lastId.incrementAndGet();
            m.setId(id);
            movies.put(id, m);
            return m;
        }
    }

    public Movie deleteMovie(long id){
        Movie m = movies.remove(id);
        reviewService.deleteReviewsofMovie(id);
        categoryService.deleteMovieFromCategories(m);
        return m;
    }
    public Collection<Movie> getAllMovies() {
        return movies.values();
    }

    public void updateMovie(long id, Movie m) {
        Movie originalMovie = movies.get(id);
        categoryService.deleteMovieFromCategories(originalMovie);
        movies.put(id, m);
        categoryService.addMovieToCategories(m);
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

        ArrayList<Category> catList = m.getCategorys();
        boolean alreadyMoty = false;
        for(Category c : catList) {
            Moty moty = categoryService.getMoty(c);
            if(moty.getName().equals(m.getName())) alreadyMoty = true;
            if((moty.getScore()==-1 && !alreadyMoty) || (moty.getScore()<m.getScore() && !moty.getName().equals(m.getName()) && !alreadyMoty)){
                moty.setScore(m.getScore());
                moty.setName(m.getName());
                motyService.updateMoty(moty.getId(),moty);
                alreadyMoty=true;
            }
        }
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

    public ArrayList<Movie> moviesOfCategory(Category c){
        ArrayList<Movie> sol = new ArrayList<>();
        for(Map.Entry entry: movies.entrySet()) {
            Movie m = (Movie)entry.getValue();
            if(isCategory(c,m)) {
                sol.add(m);
            }
        }
        return sol;
    }

}
