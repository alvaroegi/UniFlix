package com.example.uniflix.Controller;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Moty;
import com.example.uniflix.Entities.Movie;
import com.example.uniflix.Entities.Review;
import com.example.uniflix.InterfacesBBDD.CategoryRepository;
import com.example.uniflix.InterfacesBBDD.MotyRepository;
import com.example.uniflix.InterfacesBBDD.MovieRepository;
import com.example.uniflix.InterfacesBBDD.ReviewRepository;
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
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class MovieController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MotyRepository motyRepository;
    @Autowired
    ReviewRepository reviewRepository;
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
        Moty m1 = new Moty(categoryService.getCategory("Miedo"));
        miedo.setMoty(m1);
        m1.setCategory(miedo);
        //categoryService.addCategory(miedo);
        categoryRepository.save(miedo);
        //motyService.addMoty(m1);
        //motyRepository.save(m1);

        Category accion = new Category("Accion");
        Moty m2 = new Moty(categoryService.getCategory("Accion"));
        accion.setMoty(m2);
        m2.setCategory(accion);
        //categoryService.addCategory(accion);
        categoryRepository.save(accion);
        //motyService.addMoty(m2);
        //motyRepository.save(m2);

        Category drama = new Category("Drama");
        Moty m3 = new Moty(categoryService.getCategory("Drama"));
        drama.setMoty(m3);
        m3.setCategory(drama);
        //categoryService.addCategory(drama);
        categoryRepository.save(drama);
        //motyService.addMoty(m3);
        //motyRepository.save(m3);

        Category anime = new Category("Anime");
        Moty m4 = new Moty(categoryService.getCategory("Anime"));
        anime.setMoty(m4);
        m4.setCategory(anime);
        //categoryService.addCategory(anime);
        categoryRepository.save(anime);
        //motyService.addMoty(m4);
        //motyRepository.save(m4);

        Category suspense = new Category("Suspense");
        Moty m5 = new Moty(categoryService.getCategory("Suspense"));
        suspense.setMoty(m5);
        m5.setCategory(suspense);
        //categoryService.addCategory(suspense);
        categoryRepository.save(suspense);
        //motyService.addMoty(m5);
        //motyRepository.save(m5);

        //We create the initial movies
        ArrayList<Category> you = new ArrayList<>();
        you.add(categoryService.getCategory("Suspense"));
        you.add(categoryService.getCategory("Drama"));
        Movie y = new Movie("You","Greg Berlanti","Un joven profundamente obsesivo y peligrosamente seductor mueve cielo y tierra para instalarse en la vida de aquellas personas por quienes se siente cautivado",2018,"you.jpg", you);
        //moviesService.addMovie(y);
        movieRepository.save(y);
        ArrayList<Category> mand = new ArrayList<>();
        mand.add(categoryService.getCategory("Accion"));
        mand.add(categoryService.getCategory("Suspense"));
        Movie m = new Movie("The Mandalorian","Jon Favreau", "Serie de aventura espacial que se ubica en el universo de Stars Wars",2019,"mandalorian.jpg", mand);
        //moviesService.addMovie(m);
        movieRepository.save(m);
        ArrayList<Category> tlou = new ArrayList<>();
        tlou.add(categoryService.getCategory("Miedo"));
        tlou.add(categoryService.getCategory("Accion"));
        Movie t = new Movie("The Last of Us","Craig Mazin","Basado en un videojuego de accion y aventuras la serie nos relata como Joel y Ellie sobreviven a una pandemia en EEUU",2023,"thelastofus.jpg", tlou);
        //moviesService.addMovie(t);
        movieRepository.save(t);
        ArrayList<Category> naruto = new ArrayList<>();
        naruto.add(categoryService.getCategory("Anime"));
        naruto.add(categoryService.getCategory("Accion"));
        Movie n = new Movie("Naruto","Osamu Kobayashi","Basada en la historia de un ninja huérfano que aspira a convertirse en Hokage y ser alguien importante en su aldea.",2002,"naruto.jpg", naruto);
        //moviesService.addMovie(n);
        movieRepository.save(n);
        ArrayList<Category> peaky = new ArrayList<>();
        peaky.add(categoryService.getCategory("Drama"));
        peaky.add(categoryService.getCategory("Accion"));
        peaky.add(categoryService.getCategory("Suspense"));
        Movie pb = new Movie("Peaky Blinders","Otto Buthurst","Basada en la historia de una familia gitana y en su auge por convertirse en gangsters reconocidos",2013,"peakyblinders.jpg", peaky);
        //moviesService.addMovie(pb);
        movieRepository.save(pb);
        ArrayList<Category> tintina = new ArrayList<>();
        tintina.add(categoryService.getCategory("Miedo"));
        tintina.add(categoryService.getCategory("Suspense"));
        Movie tt = new Movie("Tin & Tina","Rubin Stein","Cuando Lola pierde a sus dos hijos también pierde la fe en Dios. Para intentar recuperarla adopta dos hermanos angelicales de los que empieza a sentirse atraída.",2023,"tintina.jpg", tintina);
        //moviesService.addMovie(tt);
        tt = movieRepository.save(tt);
        tt.setScore(5);
        tt = movieRepository.save(tt);

        //We create the initials reviews for the initials movies
        Review r1 = new Review("Raul", "Una pelicula que merece mucho la pena ver, la recomiendo a todos los fanáticos de Jaime Lorente",tt, 4);
        //reviewService.addReview(r1);
        reviewRepository.save(r1);
        //moviesService.updateScore(6);

        Review r2 = new Review("Alvaro", "Una de las mejores series de la época, Cillian Murphy esta espectacular", moviesService.getMovie(5), 5);
        Review real = new Review("Fauste23","La serie más sobrevalorada de la historia, es una telenovela para hombres",moviesService.getMovie(5),1);
        //reviewService.addReview(r2);
        reviewRepository.save(r2);
        //reviewService.addReview(real);
        reviewRepository.save(real);
        //moviesService.updateScore(5);

        Review r3 = new Review("Alejandro", "Una de las peores series de Netflix", moviesService.getMovie(1), 1);
        Review real2 = new Review("Experto23","La mezcla del romanticismo con la psicopatía del personaje crea un aura de suspense espectacular",moviesService.getMovie(1),5);
        //reviewService.addReview(r3);
        reviewRepository.save(r3);
        //reviewService.addReview(real2);
        reviewRepository.save(real2);
        //moviesService.updateScore(1);

        Review r4 = new Review("user123", "Una continuación de la mejor saga de la historia, pero no está a la altura", moviesService.getMovie(2), 3);

        //reviewService.addReview(r4);
        reviewRepository.save(r4);
        //moviesService.updateScore(2);

        Review r5 = new Review("lechuga", "Se podría decir que me ha gustado más que el juego y eso que tengo muchas horas jugadas", moviesService.getMovie(3), 5);
        //reviewService.addReview(r5);
        reviewRepository.save(r5);
        //moviesService.updateScore(3);

        Review r6 = new Review("alemg_29", "Si te gusta el anime y no has visto Naruto... deberías estar preso", moviesService.getMovie(4), 4);
        Review real3 = new Review("como33", "Esto solo lo ve la gente que huele mal. Que naruto y naruta mejor el nano ese puede con todo", moviesService.getMovie(4),1);
        //reviewService.addReview(r6);
        reviewRepository.save(r6);
        //reviewService.addReview(real3);
        reviewRepository.save(real3);
        //moviesService.updateScore(4);

        //motyService.updateMotysOfCategorys(categoryService.getCategorys());
    }

    @GetMapping("/")
    public String back(Model model) {
        model.addAttribute("nofilter",true);
        model.addAttribute("movies", moviesService.getSixMovies());
        model.addAttribute("categorys", categoryService.getCategorys());
        ArrayList<Movie> aux1 = moviesService.getSixMoviesofCat(categoryService.getCategory(1));
        model.addAttribute("fear",aux1);
        ArrayList<Movie> aux2 = moviesService.getSixMoviesofCat(categoryService.getCategory(2));
        model.addAttribute("action",aux2);
        ArrayList<Movie> aux3 = moviesService.getSixMoviesofCat(categoryService.getCategory(3));
        model.addAttribute("drama",aux3);
        ArrayList<Movie> aux4 = moviesService.getSixMoviesofCat(categoryService.getCategory(4));
        model.addAttribute("anime",aux4);
        ArrayList<Movie> aux5 = moviesService.getSixMoviesofCat(categoryService.getCategory(5));
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
        List<Category> categorys = categoryService.getSelectedCategorys(selectedCategorys);
        List<Category> categorys2 = categoryService.getCategorys();
        Movie m = new Movie(name, director,synopsis, year, image.getOriginalFilename(), categorys);
        model.addAttribute("categorys",categorys2);
        if(moviesService.addMovie(m)!=null) {
            model.addAttribute("added", true);
            String absolutePath = "C://Uniflix//Peliculas";
            try {
                byte[] bytesImage = image.getBytes();
                Path completePath = Paths.get(absolutePath + "/" + image.getOriginalFilename());
                Files.write(completePath, bytesImage);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
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
            long id = moviesService.containsMovie(movie);
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
            updatedMovie.setId(id);
            updatedMovie.setScore(moviesService.getMovie(id).getScore());
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

    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("nano","default.jpg");
        return "error";
    }

}