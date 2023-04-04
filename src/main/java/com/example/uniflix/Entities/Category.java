package com.example.uniflix.Entities;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Movie> movies;

    private long moty;
    private long id = -1;

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
    public void setMovies(ArrayList<Movie> m) { this.movies = m; }

    public String getName() {return this.name;}

    public long getId() { return this.id; }

    public ArrayList<Movie> getMovies() {return this.movies;}


}
