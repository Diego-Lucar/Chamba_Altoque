package com.project.sistema.chamba_altoque;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 🏠 Tu página de inicio oficial (raíz)
    @GetMapping("/")
    public String inicio() {
        return "inicio"; // Abre tu inicio.html
    }

    // 🔍 La página de profesionales de tu compañero asociada a "Servicios"
    @GetMapping("/servicios")
    public String verServicios() {
        return "profesionales"; // Abre profesionales.html
    }
}
