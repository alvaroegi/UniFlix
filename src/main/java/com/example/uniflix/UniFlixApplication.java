package com.example.uniflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class UniFlixApplication {

    public static void main(String[] args) {
        File directorio = new File("C://Uniflix//Peliculas");
        if (!directorio.exists()) {
            if(directorio.mkdirs()){
                File src = new File("src/main/resources/static/images");
                File dest = new File("C://Uniflix//Peliculas");

                try {
                    FileSystemUtils.copyRecursively(src, dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        SpringApplication.run(UniFlixApplication.class, args);
    }

}
