package com.example.uniflix.InterfacesBBDD;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
