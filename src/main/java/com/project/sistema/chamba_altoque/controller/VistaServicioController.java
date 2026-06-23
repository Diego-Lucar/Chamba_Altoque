package com.project.sistema.chamba_altoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.sistema.chamba_altoque.entities.Freelancer;
import com.project.sistema.chamba_altoque.repository.DistritoRepository;
import com.project.sistema.chamba_altoque.repository.ServicioRepository;
import com.project.sistema.chamba_altoque.service.FreelancerService;

//! DENTRO DE ESTE CONTROLLER SE MANEJARAN LOS COMPORTAMIENTOS
//! DE LA VISTA SERVICIO Y LA VISTA SOLICITAR SERVICIO
@Controller
@RequestMapping("/servicios")
public class VistaServicioController {
    private final ServicioRepository servicioRepository;
    private final DistritoRepository distritoRepository;
    private final FreelancerService freelancerService;

    public VistaServicioController(ServicioRepository servicioRepository, DistritoRepository distritoRepository,
            FreelancerService freelancerService) {
        this.servicioRepository = servicioRepository;
        this.distritoRepository = distritoRepository;
        this.freelancerService = freelancerService;
    }

    // SE CAMBIO 
    @GetMapping
    public String vistaServicio(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("listarServicios", servicioRepository.findAll());
        model.addAttribute("listarDistritos", distritoRepository.findAll());
        
        // es para verificar si el usuario escribió algo en la barra de búsqueda
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("listarFreelancers", freelancerService.buscarFreelancers(keyword));
            model.addAttribute("keyword", keyword); 
        } else {
            // Si no buscó nada, se mantedrpra el comportamiento original
            model.addAttribute("listarFreelancers", freelancerService.listarFreelancers());
        }
        return "servicio";
    }

    @GetMapping("/solicitud/{id}")
    public String vistaSolicitarServicio(@PathVariable Integer id ,Model model){
        Freelancer freelancer = freelancerService.obtenerFreelancerPorId(id); 
        model.addAttribute("freelancer", freelancer);
        return "formulario-solicitud";
    }

}
