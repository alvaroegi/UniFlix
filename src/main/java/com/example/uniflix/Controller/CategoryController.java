package com.example.uniflix.Controller;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Movie;
import com.example.uniflix.ServiceControllers.CategoryServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;

@Controller
public class CategoryController {

    @Autowired
    CategoryServiceController categoryService;

    @GetMapping("/cat/{name}")
    public String cat(Model model, @PathVariable String name){
        Category c = categoryService.getCategory(name);
        model.addAttribute("category", c);
        return "info_category";
    }


}
