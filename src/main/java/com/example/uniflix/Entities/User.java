package com.example.uniflix.Entities;

import java.util.ArrayList;

public class User {
    private String name; //Primary Key
    private String pass;
    private String age;
    private long id = -1;

    public User(String name, String pass){
        this.name = name;
        this.pass = pass;
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
    public void setAge(String year) { this.age = year;}
    public void setId(long id){
        this.id = id;
    }



}
