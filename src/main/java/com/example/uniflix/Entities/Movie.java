package com.example.uniflix.Entities;

import com.example.uniflix.Pair;

import java.util.ArrayList;

public class Movie{

    private String name; //Unique Key
    private String director;
    private float score;
    private int year;
    private String image;
    private long id = -1; //Primary Key

    //private ArrayList<Category> categorys;

    public Movie(String name, String director, int year, String image){
        this.name = name;
        this.director = director;
        this.year = year;
        this.score = 0;
        this.image = image;
        //this.categorys = new ArrayList<>();
    }

    public Movie(Movie aux){
        this.name = aux.getName();
        this.director = aux.getDirector();
        this.year = aux.getYear();
        this.score = aux.getScore();
        this.image = aux.getImage();
    }

    public String getName(){
        return name;
    }
    public String getDirector(){
        return director;
    }
    public int getYear() { return year; }
    public float getScore(){
        return score;
    }
    public long getId(){
        return id;
    }
    public String getImage() { return image; }


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
}
