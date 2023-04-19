package com.example.uniflix.Entities;

import org.springframework.beans.factory.annotation.Autowired;

public class Moty {
    private long Idcategory;
    private float score;
    private long idMovie;

    public Moty(long cat) {
        this.score=-1;
        this.Idcategory=cat;
        this.idMovie=-1;
    }

    public void setCategory(long c) {
        this.Idcategory=c;
    }

    public float getScore() {return this.score;}
    public void setScore(float s) {this.score=s;}
    public void setIdMovie(long id){this.idMovie=id;}
    public long getIdMovie(){return this.idMovie;}
    public long getIdcategory() {return this.Idcategory;}
    public long getId() {return this.id;}

    public void setId(long id) {this.id=id;}
}
