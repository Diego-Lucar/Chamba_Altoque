package com.project.sistema.chamba_altoque.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.project.sistema.chamba_altoque.entities.Usuario;
import com.project.sistema.chamba_altoque.entities.Freelancer;
import com.project.sistema.chamba_altoque.repository.DistritoRepository;
import com.project.sistema.chamba_altoque.repository.ServicioRepository;
import com.project.sistema.chamba_altoque.service.FreelancerService;
import com.project.sistema.chamba_altoque.service.UsuarioService;


@Controller
public class LoginController {

    final DistritoRepository distritoRepository;
    final UsuarioService usuarioService;
    final ServicioRepository servicioRepository;
    final FreelancerService freelancerService;

    LoginController(DistritoRepository distritoRepository, UsuarioService usuarioService,
            ServicioRepository servicioRepository, FreelancerService freelancerService) {
        this.distritoRepository = distritoRepository;
        this.usuarioService = usuarioService;
        this.servicioRepository = servicioRepository;
        this.freelancerService = freelancerService;
    }

    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(value = "tipo", required = false) String tipo, Model model) {
        if (tipo != null && (tipo.equals("usuario") || tipo.equals("freelancer"))) {
            model.addAttribute("tipoActivo", tipo);
        }
        return "login";
    }
    

    @PostMapping("/login/usuario")
    public String loginUsuario(
            @RequestParam String correo,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        return usuarioService.autenticar(correo, password)
                .map(usuario -> {
                    session.setAttribute("usuarioId", usuario.getId());
                    session.setAttribute("tipoCuenta", "usuario");
                    return "redirect:/";
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "Correo o contraseña incorrectos.");
                    model.addAttribute("tipoActivo", "usuario");
                    return "login";
                });
    }

    @PostMapping("/login/freelancer")
    public String loginFreelancer(
            @RequestParam String correo,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        return freelancerService.autenticar(correo, password)
                .map(freelancer -> {
                    session.setAttribute("freelancerId", freelancer.getId());
                    session.setAttribute("tipoCuenta", "freelancer");
                    return "redirect:/servicios";
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "Correo o contraseña incorrectos.");
                    model.addAttribute("tipoActivo", "freelancer");
                    return "login";
                });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}


