package com.example.uniflix;

public class User {
    private String name;
    private String pass;
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
    public void setName(String name){
        this.name = name;
    }
    public void setId(long id){
        this.id = id;
    }


}
