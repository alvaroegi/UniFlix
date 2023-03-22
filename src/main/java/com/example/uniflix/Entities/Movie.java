package com.example.uniflix.Entities;

import java.util.ArrayList;

public class Movie {
    private String name;
    private String director;
    //private ArrayList<String> actors;
    private float score;
    private int year;
    private String image;

    private ArrayList<Review> reviews;
    //private ArrayList<Category> categorys;
    private long id = -1;

    public Movie(String name, String director, int year, String image){
        this.name = name;
        this.director = director;
        this.year = year;
        this.score = 0;
        this.reviews = new ArrayList<>();
        this.image = image;
        //this.categorys = new ArrayList<>();
        //this.actors = new ArrayList<>();
    }

    public String getName(){
        return name;
    }
    public String getDirector(){
        return director;
    }
    public int getYear(){
        return year;
    }
    public float getScore(){
        return score;
    }
    public long getId(){
        return id;
    }
    public String getImage() { return image; }
    public ArrayList<Review> getReviews() { return reviews; }
    //public ArrayList<String> getActors() { return actors; }
    //public ArrayList<Category> getReviews() { return categorys; }

    public void setName(String name){
        this.name = name;
    }
    public void setDirector(String director){
        this.director = director;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setId(long id){
        this.id = id;
    }
    public void setScore(float score){
        this.score = score;
    }

    public void setImage(String image) { this.image = image; }
    public void addReview(Review newReview) {
        this.reviews.add(newReview);
        this.score = 0;
        for(int i=0; i<this.reviews.size(); i++) {
            this.score+=this.reviews.get(i).getScore();
        }
        this.score = this.score/this.reviews.size();
    }
}
