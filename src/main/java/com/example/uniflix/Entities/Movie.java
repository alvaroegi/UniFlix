package com.example.uniflix.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie{
    //https://www.youtube.com/watch?v=mg2E4RWbd44 conectar mysql
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movie_id")
    private long id = -1; //Primary Key
    private String name; //Unique Key
    private String director;
    private String synopsis;
    private float score;
    private int years;
    private String image;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categorys;
    @OneToMany(mappedBy="Idmovie", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;

    public Movie(String name, String director, String synopsis, int years, String image, List<Category> categorys){
        this.name = name;
        this.director = director;
        this.synopsis=synopsis;
        if(years<0) years=0;
        this.years = years;
        this.score = 0;
        this.image = image;
        this.categorys = categorys;
    }

    public Movie(Movie aux){
        this.name = aux.getName();
        this.director = aux.getDirector();
        this.synopsis=aux.getSynopsis();
        this.years = aux.getYears();
        this.score = aux.getScore();
        this.image = aux.getImage();
        this.categorys=aux.getCategorys();
    }

    public Movie() {

    }

    public String getName(){
        return name;
    }
    public String getDirector(){
        return director;
    }
    public int getYears() { return years; }
    public float getScore(){
        return score;
    }
    public long getId(){
        return id;
    }
    public String getImage() { return image; }

    public String getSynopsis() {return synopsis;}
    public List<Category> getCategorys(){return categorys;}
    public List<Review> getReviews(){return reviews;}

    public void setName(String name){
        this.name = name;
    }
    public void setDirector(String director){
        this.director = director;
    }
    public void setSynopsis(String s) {this.synopsis=s;}
    public void setYears(int years){
        this.years = years;
    }
    public void setId(long id){
        this.id = id;
    }
    public void setScore(float score){
        this.score = score;
    }

    public void setImage(String image) { this.image = image; }
    public void setCategorys(List<Category> c) { this.categorys = c; }
    public void setReviews(List<Review> r) { this.reviews = r; }
}
