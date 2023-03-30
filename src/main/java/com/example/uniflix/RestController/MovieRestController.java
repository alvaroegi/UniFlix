package com.example.uniflix.RestController;

import com.example.uniflix.Entities.Movie;
import com.example.uniflix.ServiceControllers.MovieServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MovieRestController {
    @Autowired
    MovieServiceController moviesService;

    @GetMapping("/movie")
    public Collection<Movie> allMoviesApi() {
        return moviesService.getAllMovies();
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieApi(@PathVariable long id) {
        Movie m = moviesService.getMovie(id);
        if (m != null) {
            return new ResponseEntity<>(m, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/movie")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie addMovieApi(@RequestBody Movie m) {
        //moviesService.addUser(u);
        return m;
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Movie> deleteMovieApi(@PathVariable long id) {
        Movie m = moviesService.deleteMovie(id);
        if(m!=null) {
            return new ResponseEntity<>(m, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> updateMovieApi(@PathVariable long id, @RequestBody Movie updatedMovie) {
        Movie m = moviesService.getMovie(id);
        if (m != null && moviesService.containsMovie(updatedMovie.getName())==-1) {
            updatedMovie.setId(id);
            moviesService.updateMovie(id, updatedMovie);
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
