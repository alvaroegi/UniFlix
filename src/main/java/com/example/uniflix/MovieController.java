package com.example.uniflix;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MovieController {
    @GetMapping("/peli/{name}")
    public String reseñas(Model model, @PathVariable String name){
        model.addAttribute("peli",name);
        return "reseñas_template";
    }
}
