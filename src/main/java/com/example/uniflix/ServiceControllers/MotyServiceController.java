package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Moty;
import com.example.uniflix.Entities.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MotyServiceController {
    private Map<Long, Moty> motys = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();

    public Moty addMoty(Moty m){
        long id = lastId.incrementAndGet();
        m.setId(id);
        Moty put = motys.put(id, m);
        return m;
    }

    public ArrayList<Moty> getMotys() {
        ArrayList<Moty> sol = new ArrayList<>();
        for(Map.Entry entry: motys.entrySet()) {
            Moty m = (Moty)entry.getValue();
            sol.add(m);
        }
        return sol;
    }

    public Moty getMoty(long id) {
        return motys.get(id);
    }

    public void updateMoty(long id, Moty m) {
        //Moty originalMovie = motys.get(id);
        //categoryService.deleteMovieFromCategories(originalMovie);
        motys.put(id, m);
        //categoryService.addMovieToCategories(m);
    }

    public void updateMotysOfCategorys(ArrayList<Category> categorys) {
        for(Category c : categorys) {
            Moty bestMovie = getMoty(c.getMoty());
            bestMovie.setScore(-1);
            bestMovie.setIdMovie(-1);
            for(Movie actual : c.getMovies()) {
                if(actual.getScore() > bestMovie.getScore()) {
                    bestMovie.setIdMovie(actual.getId());
                    bestMovie.setScore(actual.getScore());
                }
            }
            updateMoty(bestMovie.getId(), bestMovie);
        }
    }
}
