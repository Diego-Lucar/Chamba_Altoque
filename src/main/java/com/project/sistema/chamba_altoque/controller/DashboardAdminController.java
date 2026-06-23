package com.project.sistema.chamba_altoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.sistema.chamba_altoque.service.FreelancerService;
import com.project.sistema.chamba_altoque.service.UsuarioService;


//! ESTE CONTROLADOR ES UNICAMENTE PARA EL DASHBOARD DEL ADMINISTRADOR,
//! NO CONFUNDIR CON LOS DASHBOARD DEL USUARIO Y DEL FREELANCER.

@Controller
@RequestMapping("/dashboard")
public class DashboardAdminController {
    final UsuarioService usuarioService;
    final FreelancerService freelancerService;

    DashboardAdminController(UsuarioService usuarioService, FreelancerService freelancerService) {
        this.usuarioService = usuarioService;
        this.freelancerService = freelancerService;
    }

    @GetMapping
    public String dashboard(Model model) {
        return "dashboardIndex";
    }

    @GetMapping("/usuario")
    public String usuario(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        model.addAttribute("totalUsuarios", usuarioService.contarUsuarios());
        return "dashboardUsuario";
    }

    @GetMapping("/freelancer")
    public String freelancer(Model model) {
        model.addAttribute("freelancers", freelancerService.listarFreelancers());
        model.addAttribute("totalFreelancers", freelancerService.contarFreelancers());
        return "dashboardFreelancer";
    }

    @GetMapping("/validacion")
    public String validacion() {
        return "dashboardValidacion";
    }
}
