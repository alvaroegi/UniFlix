package com.example.uniflix;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SigningController {
    @GetMapping("/login")
    public String change1() {
        return "login";
    }

    @PostMapping("/log")
    public String log(Model model, @RequestParam String user, String pass) {
        //Usuario u = new Usuario(user, pass)
        //if(!mapa.contains(u)) => alerta
        //else => iniciar sesion con ese usuario
        //model.addAtribute("usuario", u);
        return "index";
    }

    @PostMapping("/sign")
    public String sign(Model model, @RequestParam String user, String pass) {
        //Usuario u = new Usuario(user, pass)
        //if(mapa.contains(u)) => alerta
        //else => iniciar sesion con ese usuario
        //model.addAtribute("usuario", u);
        return "index";
    }

}
