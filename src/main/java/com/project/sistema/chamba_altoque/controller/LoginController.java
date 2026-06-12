package com.project.sistema.chamba_altoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Define la URL de acceso: http://localhost:8081/login
    @GetMapping("/login")
    public String mostrarLogin() {
        // Redirecciona al archivo login.html dentro de templates
        return "login";
    }
}