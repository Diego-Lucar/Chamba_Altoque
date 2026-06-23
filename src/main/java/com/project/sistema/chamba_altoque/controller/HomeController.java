package com.project.sistema.chamba_altoque.controller;

import com.project.sistema.chamba_altoque.entities.Freelancer;

import com.project.sistema.chamba_altoque.repository.FreelancerRepository;
import com.project.sistema.chamba_altoque.repository.DistritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private FreelancerRepository freelancerRepository;
    @Autowired
    private DistritoRepository distritoRepository;

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("todosLosDistritos", distritoRepository.findAll());
        return "inicio";
    }

    @GetMapping("/servicios")
    public String verServiciosFiltrados(
            @RequestParam(value = "distrito", required = false) String distrito,
            Model model) {
        
        List<Freelancer> listaFiltrada;

        if (distrito != null && !distrito.trim().isEmpty()) {
            
            listaFiltrada = freelancerRepository.buscarPorDistrito(distrito);
        } else {
            
            listaFiltrada = freelancerRepository.findAll();
        }

        model.addAttribute("freelancers", listaFiltrada);
        model.addAttribute("distritoBuscado", distrito);

        return "servicio";
    }
}
