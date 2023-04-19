package com.example.uniflix.Entities;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Moty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id=-1;
    @OneToOne(cascade = CascadeType.ALL)
    private Category Idcategory;
    private float score;
    private long idMovie;

    public Moty() {

    }

    public Moty(Category cat) {
        this.score=-1;
        this.Idcategory=cat;
        this.idMovie=-1;
    }

    public void setCategory(Category c) {
        this.Idcategory=c;
    }

    public float getScore() {return this.score;}
    public void setScore(float s) {this.score=s;}
    public void setIdMovie(long id){this.idMovie=id;}
    public long getIdMovie(){return this.idMovie;}
    public Category getIdcategory() {return this.Idcategory;}
    public long getId() {return this.id;}

    public void setId(long id) {this.id=id;}
}
