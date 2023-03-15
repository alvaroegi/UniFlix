package com.example.uniflix;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserServiceController {
    private Map<Long, User> usuarios = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();
    public UserServiceController(){
        User alvaro = new User("alvaro", "alvaro123");
        long idA = lastId.incrementAndGet();
        alvaro.setId(idA);
        usuarios.put(idA, alvaro);

        User raul = new User("raul","raul123");
        long idR = lastId.incrementAndGet();
        raul.setId(idR);
        usuarios.put(idR, raul);

        User alex = new User("alex", "alex123");
        long idAl = lastId.incrementAndGet();
        alex.setId(idAl);
        usuarios.put(idAl, alex);
    }

    @GetMapping("/login")
    public String change1() {
        return "login";
    }
    public User logService(String user, String pass) {
        long i = 1;
        if(!usuarios.isEmpty()) {
            while(i <= lastId.longValue() && !usuarios.get(i).getName().equals(user)){
                i++;
            }
        }
        if (i > lastId.longValue() || !usuarios.get(i).getPass().equals(pass)) {
            return null;
        } else {
            User u = new User(user, pass);
            u.setId(usuarios.get(i).getId());
            return new User(user, pass);
        }
    }

    @PostMapping("/sign")
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
    }

}