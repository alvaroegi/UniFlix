package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.*;
import com.example.uniflix.InterfacesBBDD.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MovieServiceController {
    private Map<Long, Movie> movies = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();

    @Autowired
    MovieRepository movieRepo;

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
            long id = lastId.incrementAndGet();
            m.setId(id);
            categoryService.addMovieToCategories(m);
            motyService.updateMotysOfCategorys(m.getCategorys());
            movies.put(id, m);
            return m;
        }
    }

    public Movie deleteMovie(long id){
        Movie m = movies.remove(id);
        reviewService.deleteReviewsofMovie(id);
        // borrar esta peli de los motys en los que esté
        categoryService.deleteMovieFromCategories(m);
        motyService.updateMotysOfCategorys(m.getCategorys());
        return m;
    }
    public Collection<Movie> getAllMovies() {
        return movies.values();
    }

    public void updateMovie(long id, Movie m) {
        Movie originalMovie = movies.get(id);
        //borrar esta peli de los motys en los que esté
        categoryService.deleteMovieFromCategories(originalMovie);
        motyService.updateMotysOfCategorys(originalMovie.getCategorys());
        movies.put(id, m);
        categoryService.addMovieToCategories(m);
        motyService.updateMotysOfCategorys(m.getCategorys());
        updateScore(id);
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
        List<Category> clist = m.getCategorys();
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
    public ArrayList<Movie> getSixMovies(){
        List<Movie> movieList = movieRepo.findAll();
        ArrayList<Movie> sol = new ArrayList<>();
        if (movieList.size() <= 6) {
            for(Movie m : movieList) {
                sol.add(m);
            }
        } else {
            Random random = new Random();
            Set<Long> numbers = new HashSet<>();
            while (numbers.size() < 6) {
                long randomNumber = random.nextInt(lastId.intValue()) + 1;
                if(movies.get(randomNumber) != null)
                    numbers.add(randomNumber);
            }
            for(Long aux : numbers) {
                Movie m = movies.get(aux);
                sol.add(m);
            }
        }
        return sol;
    }

    public ArrayList<Movie> getSixMoviesofCat(Category c){
        ArrayList<Movie> sol = moviesOfCategory(c);
        if (sol.size() <= 6) {
            return sol;
        } else {
            ArrayList<Movie> aux = new ArrayList<>();
            Random random = new Random();
            Set<Integer> numbers = new HashSet<>();
            while (numbers.size() < 6) {
                int randomNumber = random.nextInt(sol.size());
                numbers.add(randomNumber);
            }
            for(int i : numbers) {
                aux.add(sol.get(i));
            }
            return aux;
        }
    }

}
