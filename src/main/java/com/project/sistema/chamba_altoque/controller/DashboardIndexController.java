package com.project.sistema.chamba_altoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardIndexController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboardIndex";
    }
    @GetMapping("/usuario")
    public String usuario() {
        return "dashboardUsuario";
    }

    @GetMapping("/freelancer")
    public String freelancer() {
        return "dashboardFreelancer";
    }
    @GetMapping("/validacion")
    public String validacion(){
        return "dashboardValidacion";
    }
    

}
