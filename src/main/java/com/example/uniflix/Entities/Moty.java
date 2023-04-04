package com.example.uniflix.Entities;

import org.springframework.beans.factory.annotation.Autowired;

public class Moty {
    private long Idcategory;
    private long id=-1;

    public Moty(long cat) {
        this.Idcategory=cat;
    }

    public void setCategory(long c) {
        this.Idcategory=c;
    }
}
