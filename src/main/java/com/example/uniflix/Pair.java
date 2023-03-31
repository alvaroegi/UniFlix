package com.example.uniflix;

public class Pair {
    private int num;
    private double media;

    public Pair() {
        num=0;
        media=0;
    }

    public void actMediaSum(int punt) {
        media = media*num;
        media += punt;
        num++;
        media = (double) media / num;
    }

    public void actMediaSubs(int punt) {
        media = media*num;
        media -= punt;
        num--;
        media = (double) media / num;
    }

}
