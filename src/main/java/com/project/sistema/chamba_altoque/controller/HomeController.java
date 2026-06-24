package com.project.sistema.chamba_altoque.controller;

import com.project.sistema.chamba_altoque.entities.Freelancer;

import com.project.sistema.chamba_altoque.repository.FreelancerRepository;
import com.project.sistema.chamba_altoque.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.sistema.chamba_altoque.repository.DistritoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @Autowired
    private FreelancerRepository freelancerRepository;
    @Autowired
    private ServicioRepository servicioRepository;
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
            Model model,
            HttpSession session) {
        
        List<Freelancer> listaFiltrada = new ArrayList<>();

        Object tipoCuenta = session.getAttribute("tipoCuenta");
        Object freelancerId = session.getAttribute("freelancerId");

        if ("freelancer".equals(tipoCuenta) && freelancerId instanceof Integer) {
            freelancerRepository.findById((Integer) freelancerId).ifPresent(listaFiltrada::add);
        } else if (distrito != null && !distrito.trim().isEmpty()) {
            listaFiltrada = freelancerRepository.buscarPorDistrito(distrito);
        } else {
            listaFiltrada = freelancerRepository.findAll();
        }

        model.addAttribute("listarServicios", servicioRepository.findAll());
        model.addAttribute("listarDistritos", distritoRepository.findAll());
        model.addAttribute("listarFreelancers", listaFiltrada);
        model.addAttribute("distritoBuscado", distrito);

        return "servicio";
    }
}