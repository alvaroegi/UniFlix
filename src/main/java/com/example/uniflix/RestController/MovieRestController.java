package com.example.uniflix.RestController;

import com.example.uniflix.Entities.Movie;
import com.example.uniflix.ServiceControllers.MovieServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

@RestController
public class MovieRestController {
    @Autowired
    MovieServiceController moviesService;

    @GetMapping("/api/movie")
    public Collection<Movie> allMoviesApi() {
        return moviesService.getAllMovies();
    }

    @GetMapping("/api/movie/{id}")
    public ResponseEntity<Movie> getMovieApi(@PathVariable long id) {
        Movie m = moviesService.getMovie(id);
        if (m != null) {
            return new ResponseEntity<>(m, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/movie")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie addMovieApi(@RequestBody Movie m) {
        if(moviesService.containsMovie(m.getName())==-1) {
            m.setImage("/default.jpg");
            moviesService.addMovie(m);
            return m;
        }
        return null;
    }

    @PostMapping("/api/{id}/image")
    public ResponseEntity<Movie> uploadImage(@PathVariable long id, @RequestBody MultipartFile image) {
        String absolutePath = "C://Uniflix//Peliculas";
        try {
            byte[] bytesImage = image.getBytes();
            Path completePath = Paths.get(absolutePath + "/" + image.getOriginalFilename());
            Files.write(completePath, bytesImage);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Movie m = moviesService.getMovie(id);
        m.setImage(image.getOriginalFilename());
        moviesService.updateMovie(id, m);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @DeleteMapping("/api/movie/{id}")
    public ResponseEntity<Movie> deleteMovieApi(@PathVariable long id) {
        Movie m = moviesService.deleteMovie(id);
        if(m!=null) {
            return new ResponseEntity<>(m, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/movie/{id}")
    public ResponseEntity<Movie> updateMovieApi(@PathVariable long id, @RequestBody Movie updatedMovie) {
        Movie m = moviesService.getMovie(id);
        if (m != null && moviesService.containsMovie(updatedMovie.getName())==-1) {
            updatedMovie.setId(id);
            // those parameters are not supposed to be modified
            updatedMovie.setName(moviesService.getMovie(id).getName());
            updatedMovie.setImage(moviesService.getMovie(id).getImage());
            updatedMovie.setScore(moviesService.getMovie(id).getScore());
            moviesService.updateMovie(id, updatedMovie);
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
