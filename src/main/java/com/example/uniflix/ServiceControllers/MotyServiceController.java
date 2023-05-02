package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Moty;
import com.example.uniflix.Entities.Movie;
import com.example.uniflix.InterfacesBBDD.MotyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MotyServiceController {

    @Autowired
    MotyRepository motyRepo;


    public List<Moty> getMotys() {
        List<Moty> sol = motyRepo.findAll();
        return sol;
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
        Optional<Moty> aux = motyRepo.findById(id);
        Moty updateMoty = new Moty();
        if(aux.isPresent()){
            updateMoty = aux.get();
            updateMoty.setScore(m.getScore());
            updateMoty.setIdMovie(m.getIdMovie());
        }
        motyRepo.save(m);
    }

    public void updateMotysOfCategorys(List<Category> categorys) {
        for(Category c : categorys) {
            Moty bestMovie = getRealMoty(c.getMoty().getId());
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
