package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.*;
import com.example.uniflix.InterfacesBBDD.CategoryRepository;
import com.example.uniflix.InterfacesBBDD.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class    MovieServiceController {
    private Map<Long, Movie> movies = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();
    //typedQuerys
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    MovieRepository movieRepo;
    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    ReviewServiceController reviewService;
    @Autowired
    CategoryServiceController categoryService;

    @Autowired
    MotyServiceController motyService;
    //inicializar peliculas iniciales

    public Movie getMovie(long id) {
        return movieRepo.getReferenceById(id);
    }

    public Movie getRealMovie(long id){
        Optional<Movie> aux = movieRepo.findById(id);
        Movie m = new Movie();
        if(aux.isPresent()){
            m = aux.get();
        }
        return m;
    }

    public List<Movie> getMovies() {
        List<Movie> movieList = movieRepo.findAll();
        return movieList;
    }
    public long containsMovie(String name) {
        long sol = 0;
        List<Movie> movieList = movieRepo.findAll();
        for(Movie m : movieList){
            if(m.getName().toLowerCase().replace(" ", "").equals(name.toLowerCase().replace(" ", ""))) {
                sol = (long)m.getId();
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
            //categoryService.addMovieToCategories(m);
            motyService.updateMotysOfCategorys(m.getCategorys());
            return movieRepo.save(m);
        }
    }

    public Movie deleteMovie(long id){
        Movie m = getRealMovie(id);
        //reviewService.deleteReviewsofMovie(id);
        // categoryService.deleteMovieFromCategories(m);
        movieRepo.delete(m);
        List<Category> categoryList = new ArrayList<>();
        for (Category c : m.getCategorys()) {
            c.getMovies().remove(m);
            c = categoryRepo.save(c);
            categoryList.add(c);
        }
        motyService.updateMotysOfCategorys(categoryList);
        return m;
    }
    public Collection<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public Movie updateMovie(long id, Movie m) {
        Optional<Movie> aux = movieRepo.findById(id);
        Movie originalMovie = new Movie();
        if (aux.isPresent()){
            originalMovie = aux.get();
            originalMovie.setName(m.getName());
            originalMovie.setSynopsis(m.getSynopsis());
            originalMovie.setYears(m.getYears());
            originalMovie.setDirector(m.getDirector());
            originalMovie.setCategorys(m.getCategorys());
        }
        //borrar esta peli de los motys en los que esté
        //categoryService.deleteMovieFromCategories(originalMovie);
        motyService.updateMotysOfCategorys(originalMovie.getCategorys());
        m = movieRepo.save(originalMovie);
        //categoryService.addMovieToCategories(m);
        motyService.updateMotysOfCategorys(m.getCategorys());
        updateScore(id);
        return m;
    }

    public List<Movie> searchMovies(String name){
        TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.name LIKE :name", Movie.class);
        return query.setParameter("name", "%" + name + "%").getResultList();
    }

    public void updateScore(long id) {
        List<Review> reviewList = reviewService.getReviewsOfMovie(id);
        float amount = 0;
        float total = 0;
        for(Review r : reviewList) {
            amount++;
            total = total + r.getScore();
        }
        Optional<Movie> aux = movieRepo.findById(id);
        Movie m = new Movie();
        if(aux.isPresent()){
            m = aux.get();
            if(amount!=0)
                m.setScore((float)Math.round(total/amount*100)/100);
            else
                m.setScore(0);
        }
        movieRepo.save(m);
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
        List<Movie> movieList = movieRepo.findAll();
        for(Movie m : movieList)
            if(isCategory(c,m)) {
                sol.add(m);
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
                long randomNumber = random.nextInt(movieList.size());
                if(movieList.get((int)randomNumber) != null)
                    numbers.add(randomNumber);
            }
            for(long aux : numbers) {
                Movie m = movieList.get((int) aux);
                sol.add(m);
            }
        }
        return sol;
    }

    public List<Movie> getSixMoviesofCat(Category c) {
        List<Movie> movieList = c.getMovies();
        if (movieList.size() <= 6) {
            return movieList;
        } else {
            List<Movie> aux = new ArrayList<>();
            Random random = new Random();
            Set<Integer> numbers = new HashSet<>();
            while (numbers.size() < 6) {
                int randomNumber = random.nextInt(movieList.size());
                numbers.add(randomNumber);
            }
            for (int i : numbers) {
                aux.add(movieList.get(i));
            }
            return aux;
        }
    }
}
