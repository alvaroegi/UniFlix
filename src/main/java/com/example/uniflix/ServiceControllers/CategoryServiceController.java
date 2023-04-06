package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Moty;
import com.example.uniflix.Entities.Movie;
import com.example.uniflix.Entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CategoryServiceController {

    @Autowired
    MotyServiceController motyService;
    private Map<Long, Category> categorys = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();


    public Category addCategory(Category c){
        long id = lastId.incrementAndGet();
        c.setId(id);
        categorys.put(id, c);
        return c;
    }

    public LinkedList<Category> getCategorys() {
        LinkedList<Category> catList = new LinkedList<>();
        for(Map.Entry entry: categorys.entrySet()) {
            Category c = (Category) entry.getValue();
            catList.add(c);
        }
        return catList;
    }

    public Category getCategory(String name) {
        Category sol = new Category("");
        for(Map.Entry entry: categorys.entrySet()) {
            Category c = (Category) entry.getValue();
            String aux = c.getName();
            if(aux.equals(name)) {
                sol = c;
            }
        }
        return sol;
    }

    public Category getCategory(long id) {
        return categorys.get(id);
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
            ArrayList<Movie> movieList = aux.getMovies();
            movieList.add(m);
            aux.setMovies(movieList);
            categorys.put(aux.getId(), aux);
        }
    }
    public void deleteMovieFromCategories(Movie m) {
        for(Category c : m.getCategorys()) {
            Category aux = getCategory(c.getName());
            ArrayList<Movie> movieList = aux.getMovies();
            movieList.remove(m);
            aux.setMovies(movieList);
            categorys.put(aux.getId(), aux);
        }
    }

    public Moty getMoty(Category c) {
        ArrayList<Moty> list = motyService.getMotys();
        String name = c.getName();
        Moty sol = new Moty(1);
        for(Moty aux : list) {
            String other = categorys.get(aux.getIdcategory()).getName();
            if(name.equals(other)) {
                sol = aux;
            }
        }
        return sol;
    }


}
