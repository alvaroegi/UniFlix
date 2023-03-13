package com.example.uniflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniFlixApplication {

    public static void main(String[] args) {

        SpringApplication.run(UniFlixApplication.class, args);
        SigningController sg = new SigningController();
    }

}
