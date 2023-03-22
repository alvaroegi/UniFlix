package com.example.uniflix.Entities;

public class Review {
    private String user;
    private String comment;
    private String movie;
    private int score;
    private long id = -1;

    public Review(String user, String comment,String movie, int score){
        this.user = user;
        this.comment = comment;
        this.movie = movie;
        this.score = score;
    }

    public String getUser(){
        return user;
    }
    public String getMovie(){
        return movie;
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
    public void setMovie(String movie){
        this.movie = movie;
    }
    public void setId(long id){
        this.id = id;
    }
    public void setScore(int score){
        this.score = score;
    }

}
