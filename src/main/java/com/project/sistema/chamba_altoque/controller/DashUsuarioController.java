package com.project.sistema.chamba_altoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashUsuarioController {

    @GetMapping("/dashboard-usuario")
    public String mostrarDashboardUsuario(Model model) {
        // Dejado listo para recibir los datos dinámicos del usuario logueado en un futuro
        return "dashUsuario";
    }
}
