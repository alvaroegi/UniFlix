package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Moty;
import com.example.uniflix.InterfacesBBDD.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CategoryServiceController {

    @Autowired
    MotyServiceController motyService;
    @Autowired
    CategoryRepository categoryRepo;

    public List<Category> getCategorys() {
        List<Category> catList = categoryRepo.findAll();
        return catList;
    }

    public Category getCategory(String name) {
        Category sol = new Category("");
        List<Category> catList = categoryRepo.findAll();
        for(Category c : catList) {
            String aux = c.getName().toLowerCase();
            if(aux.equals(name.toLowerCase())) {
                sol = c;
            }
        }
        return sol;
    }

    public Category getRealCategory(long id){
        Optional<Category> aux = categoryRepo.findById(id);
        Category m = new Category();
        if(aux.isPresent()){
            m = aux.get();
        }
        return m;
    }

    public ArrayList<Category> getSelectedCategorys(String[] categoryList) {
        ArrayList<Category> selectedCategorys = new ArrayList<>();
        for (int i = 0; i < categoryList.length; i++)
            selectedCategorys.add(getCategory(categoryList[i]));
        return selectedCategorys;
    }


    public Moty getMoty(Category c) {
        return c.getMoty();
    }


}
