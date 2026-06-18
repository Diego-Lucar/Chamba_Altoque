package com.project.sistema.chamba_altoque.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import com.project.sistema.chamba_altoque.entities.Usuario;
import com.project.sistema.chamba_altoque.repository.DistritoRepository;
import com.project.sistema.chamba_altoque.service.UsuarioService;


@Controller
public class LoginController {

    final DistritoRepository distritoRepository;
    final UsuarioService usuarioService;

    LoginController(DistritoRepository distritoRepository, UsuarioService usuarioService) {
        this.distritoRepository = distritoRepository;
        this.usuarioService = usuarioService;
    }



    // Define la URL de acceso: http://localhost:8081/login
    @GetMapping("/login")
    public String mostrarLogin() {
        // Redirecciona al archivo login.html dentro de templates
        return "login";
    }

    @GetMapping("/registro")
    public String vistaRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("distritos", distritoRepository.findAll());
        return "registro";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("distritos", distritoRepository.findAll());
            return "registroPrueba";
        }
        usuarioService.guardarUsuario(usuario);
        return "redirect:/";
    }
    
}