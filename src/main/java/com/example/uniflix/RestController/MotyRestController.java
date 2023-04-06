package com.example.uniflix.RestController;

import com.example.uniflix.Entities.Moty;
import com.example.uniflix.ServiceControllers.MotyServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class MotyRestController {
    @Autowired
    MotyServiceController motyService;
    @GetMapping("/api/moty")
    public Collection<Moty> allMotysApi() {
        return motyService.getMotys();
    }

    @GetMapping("/api/moty/{id}")
    public ResponseEntity<Moty> getMovieApi(@PathVariable long id) {
        Moty m = motyService.getMoty(id);
        if (m != null) {
            return new ResponseEntity<>(m, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
