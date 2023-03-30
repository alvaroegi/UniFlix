package com.example.uniflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootApplication
public class UniFlixApplication {

    public static void main(String[] args) {
        File directorio = new File("C://Uniflix//Peliculas");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        SpringApplication.run(UniFlixApplication.class, args);
    }

}
