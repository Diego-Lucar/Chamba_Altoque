package com.project.sistema.chamba_altoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.sistema.chamba_altoque.repository.DistritoRepository;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    
    private final DistritoRepository distritoRepository;

    public HomeController(DistritoRepository distritoRepository) {
        this.distritoRepository = distritoRepository;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("listarDistritos", distritoRepository.findAll());
        return "inicio";
    }

    @GetMapping("/como-funciona")
    public String mostrarComoFunciona() {
        return "como-funciona";
    }

}
