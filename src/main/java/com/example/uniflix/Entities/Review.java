package com.example.uniflix.Entities;


import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String user;
    private String comment;
    @ManyToOne(cascade = CascadeType.ALL)
    private Movie Idmovie;
    private int score;
    private long id = -1;

    public Review(String user, String comment, Movie movie, int score){
        this.user = user;
        this.comment = comment;
        this.Idmovie = movie;
        if(score>5) score=5;
        else if(score<0) score=0;
        this.score = score;
    }

    public Review() {

    }

    public String getUser(){
        return user;
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

    public void setUser(String user){
        this.user = user;
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
