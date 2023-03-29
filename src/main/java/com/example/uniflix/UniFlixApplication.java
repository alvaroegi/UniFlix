package com.example.uniflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class UniFlixApplication {

    public static void main(String[] args) {
        File directorio = new File("C://Uniflix//Peliculas");
        if(!directorio.exists()) {
            directorio.mkdirs();
            File src = new File("src/main/resources/static/images");
            File dest = new File("C://Uniflix//Peliculas");
            try {
                FileSystemUtils.copyRecursively(src, dest);
            }
            catch(IOException e) {

            }
        }
        SpringApplication.run(UniFlixApplication.class, args);
    }

}
