package com.project.sistema.chamba_altoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashFreelancerController {

    @GetMapping("/dashboard-freelancer")
    public String mostrarDashboardFreelancer() {
        
        return "dashFreelancer";
    }
}