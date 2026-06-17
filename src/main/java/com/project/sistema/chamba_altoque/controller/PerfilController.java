package com.project.sistema.chamba_altoque.controller;

import com.project.sistema.chamba_altoque.entities.Usuario;
import com.project.sistema.chamba_altoque.entities.Distrito;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PerfilController {

    @GetMapping("/perfil")
    public String mostrarPerfil(Model model) {
        // En un flujo real, aquí buscarías el usuario logueado en la Base de Datos
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan Pérez");
        usuario.setCorreo("juan.perez@email.com");
        usuario.setTelefono("+51 987 654 321");
        
        // Simulamos una lista de distritos para llenar el select de forma dinámica
        List<Distrito> distritos = new ArrayList<>();
        // Asumiendo que tu entidad Distrito tiene un constructor o setters
        
        model.addAttribute("usuario", usuario);
        model.addAttribute("distritos", distritos);
        
        return "perfil";
    }

    // Endpoint preparado para recibir los cambios de información personal
    @PostMapping("/perfil/actualizar-info")
    public String actualizarInformacion(@ModelAttribute("usuario") Usuario usuario) {
        // Aquí irá la lógica de tu service: usuarioService.guardar(usuario);
        return "redirect:/perfil?successInfo";
    }

    // Endpoint preparado para recibir el cambio de contraseña
    @PostMapping("/perfil/actualizar-password")
    public String actualizarPassword(String passwordActual, String nuevoPassword, String confirmarPassword) {
        // Aquí irá tu lógica de validación y encriptación de contraseña
        return "redirect:/perfil?successPassword";
    }
}