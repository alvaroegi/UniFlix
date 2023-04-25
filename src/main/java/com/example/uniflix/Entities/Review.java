package com.example.uniflix.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1;
    private String name;
    private String comment;
    @ManyToOne
    private Movie Idmovie;
    private int score;

    public Review(String name, String comment, Movie movie, int score){
        this.name = name;
        this.comment = comment;
        this.Idmovie = movie;
        if(score>5) score=5;
        else if(score<0) score=0;
        this.score = score;
    }



    public Review() {

    }

    public String getName(){
        return name;
    }
    public Movie getMovie(){
        return Idmovie;
    }
    public String getComment(){
        return comment;
    }
    public int getScore(){
        return score;
    }
    public long getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public void setMovie(Movie Idmovie){
        this.Idmovie = Idmovie;
    }
    public void setId(long id){
        this.id = id;
    }
    public void setScore(int score){
        this.score = score;
    }

}
