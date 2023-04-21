package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Moty;
import com.example.uniflix.Entities.Movie;
import com.example.uniflix.InterfacesBBDD.MotyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MotyServiceController {
    private Map<Long, Moty> motys = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();

    @Autowired
    MotyRepository motyRepo;

    public Moty addMoty(Moty m){
        return motyRepo.save(m);
    }

    public List<Moty> getMotys() {
        List<Moty> sol = motyRepo.findAll();
        return sol;
    }

    public Moty getMoty(long id) {
        return motyRepo.getReferenceById(id);
    }

    public Moty getRealMoty(long id) {
        Optional<Moty> aux = motyRepo.findById(id);
        Moty m = new Moty();
        if(aux.isPresent()){
            m = aux.get();
        }
        return m;
    }

    public void updateMoty(long id, Moty m) {
        //Moty originalMovie = motys.get(id);
        //categoryService.deleteMovieFromCategories(originalMovie);
        Optional<Moty> aux = motyRepo.findById(id);
        Moty updateMoty = new Moty();
        if(aux.isPresent()){
            updateMoty = aux.get();
            updateMoty.setScore(m.getScore());
            updateMoty.setIdMovie(m.getIdMovie());
        }
        motyRepo.save(m);
        //categoryService.addMovieToCategories(m);
    }

    public void updateMotysOfCategorys(List<Category> categorys) {
        for(Category c : categorys) {
            Moty bestMovie = getRealMoty(c.getId());
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
