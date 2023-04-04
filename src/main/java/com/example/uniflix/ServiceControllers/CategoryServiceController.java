package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Movie;
import com.example.uniflix.Entities.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CategoryServiceController {
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

}
