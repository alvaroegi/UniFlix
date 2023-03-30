package com.example.uniflix.Entities;

public class Review {
    private String user;
    private String comment;
    private Long Idmovie;
    private int score;
    private long id = -1;

    public Review(String user, String comment,Long movie, int score){
        this.user = user;
        this.comment = comment;
        this.Idmovie = movie;
        this.score = score;
    }

    public String getUser(){
        return user;
    }
    public long getMovie(){
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
    public void setMovie(Long movie){
        this.Idmovie = movie;
    }
    public void setId(long id){
        this.id = id;
    }
    public void setScore(int score){
        this.score = score;
    }

}
