package com.example.uniflix.Controller;

import com.example.uniflix.ServiceControllers.ReviewServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {
    @Autowired
    ReviewServiceController reviewService;

    @PostMapping("/newReview")
    public  void newReview(Model model, @RequestParam String user, @RequestParam String comment, @RequestParam int score, @RequestParam String peli) {

    }
}
