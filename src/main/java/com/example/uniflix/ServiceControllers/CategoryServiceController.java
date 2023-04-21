package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Moty;
import com.example.uniflix.Entities.Movie;
import com.example.uniflix.Entities.Review;
import com.example.uniflix.InterfacesBBDD.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CategoryServiceController {

    @Autowired
    MotyServiceController motyService;
    @Autowired
    CategoryRepository categoryRepo;

    private Map<Long, Category> categorys = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();


    public Category addCategory(Category c){
        /*long id = lastId.incrementAndGet();
        c.setId(id);
         */
        categoryRepo.save(c);
        return c;
    }

    public List<Category> getCategorys() {
        List<Category> catList = categoryRepo.findAll();
        return catList;
    }

    public Category getCategory(String name) {
        Category sol = new Category("");
        List<Category> catList = categoryRepo.findAll();
        for(Category c : catList) {
            String aux = c.getName();
            if(aux.equals(name)) {
                sol = c;
            }
        }
        return sol;
    }

    public Category getCategory(long id) {
        return categoryRepo.getReferenceById(id);
    }

    public ArrayList<Category> getSelectedCategorys(String[] categoryList) {
        ArrayList<Category> selectedCategorys = new ArrayList<>();
        for (int i = 0; i < categoryList.length; i++)
            selectedCategorys.add(getCategory(categoryList[i]));
        return selectedCategorys;
    }

    public void addMovieToCategories(Movie m) {
        for(Category c : m.getCategorys()) {
            Category aux = getCategory(c.getName());
            List<Movie> movieList = aux.getMovies();
            movieList.add(m);
            aux.setMovies(movieList);
            categorys.put(aux.getId(), aux);
        }
    }
    public void deleteMovieFromCategories(Movie m) {
        for(Category c : m.getCategorys()) {
            Category aux = getCategory(c.getName());
            List<Movie> movieList = aux.getMovies();
            movieList.remove(m);
            aux.setMovies(movieList);
            categorys.put(aux.getId(), aux);
        }
    }


    public Moty getMoty(Category c) {
        return c.getMoty();
    }


}
