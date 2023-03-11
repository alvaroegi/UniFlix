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
    public void SigningController(){
        Usuario alvaro = new Usuario("alvaro", "alvaro123");
        long idA = lastId.incrementAndGet();
        usuarios.put(idA, alvaro);

        Usuario raul = new Usuario("raul","raul123");
        long idR = lastId.incrementAndGet();
        usuarios.put(idR, raul);

        Usuario alex = new Usuario("alex", "alex123");
        long idAl = lastId.incrementAndGet();
        usuarios.put(idAl, alex);
    }

    @GetMapping("/login")
    public String change1() {
        return "login";
    }

    @PostMapping("/log")
    public String log(Model model, @RequestParam String user, @RequestParam String pass) {
        long i = 1;
        Usuario u = null;
        if(!usuarios.isEmpty()) {
            u = usuarios.get(i);
            while (!(u.getName().equals(user)) && i <= lastId.longValue()) {
                i++;
            }
        }

        if (i > lastId.longValue()) {
            model.addAttribute("noexists", true);
            return "login";
        } else {
            model.addAttribute("user", u);
            return "success";
        }

    }

    @PostMapping("/sign")
    public String sign(Model model, @RequestParam String user, @RequestParam String pass) {
        long i = 0;
        Usuario u = null;
        if(!usuarios.isEmpty()) {
            u = usuarios.get(i);
            while (!(u.getName().equals(user)) && i <= lastId.longValue()) {
                i++;
            }
        }
        if(i != 0 && i <= lastId.longValue()){
            model.addAttribute("exists", true);
        } else {
            model.addAttribute("correct", true);
            long id = lastId.incrementAndGet();
            u = new Usuario(user, pass);
            u.setId(id);
            usuarios.put(id, u);
        }
        return "login";
    }

}
