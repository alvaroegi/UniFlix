package com.example.uniflix.RestController;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.ServiceControllers.CategoryServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CategoryRestController {
    @Autowired
    CategoryServiceController categoryService;

    @GetMapping("/api/category")
    public Collection<Category> allCategorysApi() {
        return categoryService.getCategorys();
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<Category> getCategoryApi(@PathVariable long id) {
        Category c = categoryService.getRealCategory(id);
        if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
