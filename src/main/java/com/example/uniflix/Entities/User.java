package com.example.uniflix.Entities;

import java.util.ArrayList;

public class User {
    private String name;
    private String pass;
    private long id = -1;
    private ArrayList<Long> opinions;

    public User(String name, String pass){
        this.name = name;
        this.pass = pass;
        opinions = new ArrayList<>();
    }

    public String getName(){
        return name;
    }
    public String getPass(){
        return pass;
    }
    public long getId(){
        return id;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setId(long id){
        this.id = id;
    }

    public void addOpinion(long id) {
        opinions.add(id);
    }


}
