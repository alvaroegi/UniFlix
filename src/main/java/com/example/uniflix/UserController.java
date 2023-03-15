package com.example.uniflix;
import com.example.uniflix.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class UserController {

    @Autowired
    UserServiceController usersService;

    @GetMapping("/login")
    public String change1() {
        return "login";
    }

    @PostMapping("/log")
    public String log(Model model, @RequestParam String user, @RequestParam String pass) {
        User u = usersService.logService(user, pass);
        if(u==null) {
            model.addAttribute("noexists", true);
            return "login";
        }
        else {
            model.addAttribute("user", u);
            return "sucess";
        }
    }

    /*@PostMapping("/sign")
    public String sign(Model model, @RequestParam String user, @RequestParam String pass) {
        long i = 1;
        if(!usuarios.isEmpty()) {
            while(i <= lastId.longValue() && !usuarios.get(i).getName().equals(user)){
                i++;
            }
        }

        if(i <= usuarios.size() && (usuarios.get(i).getName().equals(user))){
            model.addAttribute("exists", true);
        } else {
            User u = new User(user, pass);
            model.addAttribute("correct", true);
            long id = lastId.incrementAndGet();
            u.setId(id);
            usuarios.put(id, u);
        }
        return "login";
    }*/

}