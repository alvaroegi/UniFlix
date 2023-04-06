package com.example.uniflix.Controller;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Moty;
import com.example.uniflix.Entities.Movie;
import com.example.uniflix.Entities.Review;
import com.example.uniflix.ServiceControllers.CategoryServiceController;
import com.example.uniflix.ServiceControllers.MotyServiceController;
import com.example.uniflix.ServiceControllers.MovieServiceController;
import com.example.uniflix.ServiceControllers.ReviewServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

@Controller
public class MovieController {
    @Autowired
    MovieServiceController moviesService;
    @Autowired
    ReviewServiceController reviewService;

    @Autowired
    CategoryServiceController categoryService;
    @Autowired
    MotyServiceController motyService;

    @PostConstruct
    public void init(){
        //We create the default categorys and motys
        Category miedo = new Category("Miedo");
        miedo.setMoty(1);
        categoryService.addCategory(miedo);
        Moty m1 = new Moty(categoryService.getCategory("Miedo").getId());
        motyService.addMoty(m1);
        Category accion = new Category("Accion");
        accion.setMoty(2);
        categoryService.addCategory(accion);
        Moty m2 = new Moty(categoryService.getCategory("Accion").getId());
        motyService.addMoty(m2);
        Category drama = new Category("Drama");
        drama.setMoty(3);
        categoryService.addCategory(drama);
        Moty m3 = new Moty(categoryService.getCategory("Drama").getId());
        motyService.addMoty(m3);
        Category anime = new Category("Anime");
        anime.setMoty(4);
        categoryService.addCategory(anime);
        Moty m4 = new Moty(categoryService.getCategory("Anime").getId());
        motyService.addMoty(m4);
        Category suspense = new Category("Suspense");
        suspense.setMoty(5);
        categoryService.addCategory(suspense);
        Moty m5 = new Moty(categoryService.getCategory("Suspense").getId());
        motyService.addMoty(m5);

        //We create the initial movies
        ArrayList<Category> you = new ArrayList<>();
        you.add(categoryService.getCategory("Suspense"));
        you.add(categoryService.getCategory("Drama"));
        Movie y = new Movie("You","Greg Berlanti","Un joven profundamente obsesivo y peligrosamente seductor mueve cielo y tierra para instalarse en la vida de aquellas personas por quienes se siente cautivado",2018,"you.jpg", you);
        moviesService.addMovie(y);
        ArrayList<Category> mand = new ArrayList<>();
        mand.add(categoryService.getCategory("Accion"));
        mand.add(categoryService.getCategory("Suspense"));
        Movie m = new Movie("The Mandalorian","Jon Favreau", "Serie de aventura espacial que se ubica en el universo de Stars Wars",2019,"mandalorian.jpg", mand);
        moviesService.addMovie(m);
        ArrayList<Category> tlou = new ArrayList<>();
        tlou.add(categoryService.getCategory("Miedo"));
        tlou.add(categoryService.getCategory("Accion"));
        Movie t = new Movie("The Last of Us","Craig Mazin","Basado en un videojuego de accion y aventuras la serie nos relata como Joel y Ellie sobreviven a una pandemia en EEUU",2023,"thelastofus.jpg", tlou);
        moviesService.addMovie(t);
        ArrayList<Category> naruto = new ArrayList<>();
        naruto.add(categoryService.getCategory("Anime"));
        naruto.add(categoryService.getCategory("Accion"));
        Movie n = new Movie("Naruto","Osamu Kobayashi","Basada en la historia de un ninja huérfano que aspira a convertirse en Hokage y ser alguien importante en su aldea.",2002,"naruto.jpg", naruto);
        moviesService.addMovie(n);
        ArrayList<Category> peaky = new ArrayList<>();
        peaky.add(categoryService.getCategory("Drama"));
        peaky.add(categoryService.getCategory("Accion"));
        peaky.add(categoryService.getCategory("Suspense"));
        Movie pb = new Movie("Peaky Blinders","Otto Buthurst","Basada en la historia de una familia gitana y en su auge por convertirse en gangsters reconocidos",2013,"peakyblinders.jpg", peaky);
        moviesService.addMovie(pb);
        ArrayList<Category> tintina = new ArrayList<>();
        tintina.add(categoryService.getCategory("Miedo"));
        tintina.add(categoryService.getCategory("Suspense"));
        Movie tt = new Movie("Tin & Tina","Rubin Stein","Cuando Lola pierde a sus dos hijos también pierde la fe en Dios. Para intentar recuperarla adopta dos hermanos angelicales de los que empieza a sentirse atraída.",2023,"tintina.jpg", tintina);
        moviesService.addMovie(tt);

        //We create the initials reviews for the initials movies
        Review r1 = new Review("Raul", "Una pelicula que merece mucho la pena ver, la recomiendo a todos los fanáticos de Jaime Lorente", 6, 4);
        reviewService.addReview(r1);
        moviesService.updateScore(6);

        Review r2 = new Review("Alvaro", "Una de las mejores series de la época, Cillian Murphy esta espectacular", 5, 5);
        reviewService.addReview(r2);
        moviesService.updateScore(5);

        Review r3 = new Review("Alejandro", "Una de las peores series de Netflix", 1, 1);
        reviewService.addReview(r3);
        moviesService.updateScore(1);

        Review r4 = new Review("user123", "Una continuación de la mejor saga de la historia, pero no está a la altura", 2, 3);
        reviewService.addReview(r4);
        moviesService.updateScore(2);

        Review r5 = new Review("lechuga", "Se podría decir que me ha gustado más que el juego y eso que tengo muchas horas jugadas", 3, 5);
        reviewService.addReview(r5);
        moviesService.updateScore(3);

        Review r6 = new Review("alemg_29", "Si te gusta el anime y no has visto Naruto... deberías estar preso", 4, 4);
        reviewService.addReview(r6);
        moviesService.updateScore(4);
    }

    @GetMapping("/")
    public String back(Model model) {
        model.addAttribute("movies", moviesService.getMovies());
        model.addAttribute("categorys", categoryService.getCategorys());
        ArrayList<Movie> aux1 = moviesService.moviesOfCategory(categoryService.getCategory(1));
        model.addAttribute("fear",aux1);
        ArrayList<Movie> aux2 = moviesService.moviesOfCategory(categoryService.getCategory(2));
        model.addAttribute("action",aux2);
        ArrayList<Movie> aux3 = moviesService.moviesOfCategory(categoryService.getCategory(3));
        model.addAttribute("drama",aux3);
        ArrayList<Movie> aux4 = moviesService.moviesOfCategory(categoryService.getCategory(4));
        model.addAttribute("anime",aux4);
        ArrayList<Movie> aux5 = moviesService.moviesOfCategory(categoryService.getCategory(5));
        model.addAttribute("suspense",aux5);
        return "index";
    }

    @GetMapping("/peli/{name}")
    public String reseñas(Model model, @PathVariable String name){
        Movie m = moviesService.getMovie(moviesService.containsMovie(name));
        model.addAttribute("movie", m);
        model.addAttribute("categorys",categoryService.getCategorys());
        if(!reviewService.getReviewsOfMovie(moviesService.containsMovie(name)).isEmpty())
            model.addAttribute("reviewList", reviewService.getReviewsOfMovie(moviesService.containsMovie(name)));
        return "info_movie";
    }
    @GetMapping("/createMovie")
    public String changeToMovie(Model model) {
        model.addAttribute("categorys", categoryService.getCategorys());
        return "create_movie"; }
    @PostMapping("/result")                                                                                                /*para las imagenes-----------------------*/
    public String checkMovie(Model model, @RequestParam String name, @RequestParam String director,@RequestParam String synopsis, @RequestParam int year, @RequestParam/*("file")*/ MultipartFile image, @RequestParam String[] selectedCategorys) {
        ArrayList<Category> categorys = categoryService.getSelectedCategorys(selectedCategorys);
        LinkedList<Category> categorys2 = categoryService.getCategorys();
        Movie m = new Movie(name, director,synopsis, year, image.getOriginalFilename(), categorys);
        model.addAttribute("categorys",categorys2);
        if(moviesService.addMovie(m)!=null) {
            model.addAttribute("added", true);
            /* desde aqui para imagenes */
            // https://www.youtube.com/watch?v=BjHEuNdpC-U parte 1
            // https://www.youtube.com/watch?v=df67kmObW7M parte 2
            // para solucionar lo de las imagenes => https://www.youtube.com/watch?v=vVCgPzjbh2s
            //Path imagesDirectory = Paths.get("src/main/resources/static/images");
            //String absolutePath = imagesDirectory.toFile().getAbsolutePath();
            String absolutePath = "C://Uniflix//Peliculas";
            try {
                byte[] bytesImage = image.getBytes();
                Path completePath = Paths.get(absolutePath + "/" + image.getOriginalFilename());
                Files.write(completePath, bytesImage);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            /* hasta aqui lo de las imagenes */
        }
        else
            model.addAttribute("notAdded", true);
        model.addAttribute("categorys", categoryService.getCategorys());
        return "create_movie";
    }

    @GetMapping("/modify")
    public String update(Model model) {
        model.addAttribute("movies", moviesService.getMovies());
        model.addAttribute("categorys", categoryService.getCategorys());
        return "update_movie";
    }
    @PostMapping("/modify_delete")
    public String update_delete(Model model, @RequestParam String movie, @RequestParam boolean confirmed) {
        if(confirmed) {
            moviesService.deleteMovie(moviesService.containsMovie(movie));
            model.addAttribute("modified", true);
        }
        model.addAttribute("movies", moviesService.getMovies());
        model.addAttribute("categorys", categoryService.getCategorys());
        return "update_movie";
    }

    @PostMapping("/modify_nondelete")
    public String update_nondelete(Model model, @RequestParam String movie, @RequestParam String director, @RequestParam int year,@RequestParam String synopsis, @RequestParam String[] selectedCategorys) {
        long id = moviesService.containsMovie(movie);
        if(id!=-1) {
            ArrayList<Category> categorys = categoryService.getSelectedCategorys(selectedCategorys);
            Movie updatedMovie = new Movie(movie, director, synopsis, year, moviesService.getMovie(id).getImage(), categorys);
            moviesService.updateMovie(id, updatedMovie);
        }
        model.addAttribute("modified", true);
        model.addAttribute("movies", moviesService.getMovies());
        model.addAttribute("categorys", categoryService.getCategorys());
        return "update_movie";
    }

    @GetMapping("/search")
    public String searchFilms(Model model, @RequestParam String searching) {
        model.addAttribute("filter", searching);
        model.addAttribute("moviesWithFilter", moviesService.searchMovies(searching));
        model.addAttribute("categorys", categoryService.getCategorys());
        return "index";
    }

}