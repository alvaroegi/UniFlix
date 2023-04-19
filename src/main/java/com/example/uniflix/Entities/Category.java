package com.example.uniflix.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id = -1;
    private String name;
    @JsonIgnore
    @ManyToMany
    private List<Movie> movies;
    @OneToOne(cascade = CascadeType.ALL)
    private Moty moty;

    public Category(){
    }
    public Category(String n) {
        this.name = n;
        movies = new ArrayList<>();
    }

    public void setName(String other) {
        this.name = other;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setMovies(List<Movie> m) { this.movies = m; }

    public String getName() {return this.name;}

    public long getId() { return this.id; }
    public void setMoty(Moty moty) { this.moty = moty; }

    public List<Movie> getMovies() {return this.movies;}

    public Moty getMoty() { return this.moty; }


}
