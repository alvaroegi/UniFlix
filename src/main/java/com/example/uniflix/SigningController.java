package com.example.uniflix;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class SigningController {
    private Map<Long, Usuario> usuarios = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();
    public SigningController(){
        Usuario alvaro = new Usuario("alvaro", "alvaro123");
        long idA = lastId.incrementAndGet();
        alvaro.setId(idA);
        usuarios.put(idA, alvaro);

        Usuario raul = new Usuario("raul","raul123");
        long idR = lastId.incrementAndGet();
        raul.setId(idR);
        usuarios.put(idR, raul);

        Usuario alex = new Usuario("alex", "alex123");
        long idAl = lastId.incrementAndGet();
        alex.setId(idAl);
        usuarios.put(idAl, alex);
    }

    @GetMapping("/login")
    public String change1() {
        return "login";
    }

    @PostMapping("/log")
    public String log(Model model, @RequestParam String user, @RequestParam String pass) {
        long i = 1;
        if(!usuarios.isEmpty()) {
            while(i <= lastId.longValue() && !usuarios.get(i).getName().equals(user)){
                i++;
            }
        }

        if (i > lastId.longValue() || !usuarios.get(i).getPass().equals(pass)) {
            model.addAttribute("noexists", true);
            return "login";
        } else {
            model.addAttribute("user", new Usuario(user, pass));
            return "success";
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

        if(!usuarios.isEmpty() && (usuarios.get(i).getName().equals(user))){
            model.addAttribute("exists", true);
        } else {
            Usuario u = new Usuario(user, pass);
            model.addAttribute("correct", true);
            long id = lastId.incrementAndGet();
            u.setId(id);
            usuarios.put(id, u);
        }
        return "login";
    }

}
