package com.example.uniflix.Entities;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Long> movies;
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

    public String getName() {return this.name;}

    public ArrayList<Long> getMovies() {return this.movies;}


}
