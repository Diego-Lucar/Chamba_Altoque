package com.project.sistema.chamba_altoque.controller;

import com.project.sistema.chamba_altoque.service.FreelancerService;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import com.project.sistema.chamba_altoque.entities.Documento;
import com.project.sistema.chamba_altoque.entities.Freelancer;
import com.project.sistema.chamba_altoque.entities.Usuario;
import com.project.sistema.chamba_altoque.repository.DistritoRepository;
import com.project.sistema.chamba_altoque.repository.ServicioRepository;
import com.project.sistema.chamba_altoque.service.UsuarioService;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    private final FreelancerService freelancerService;
    final DistritoRepository distritoRepository;
    final ServicioRepository servicioRepository;
    final UsuarioService usuarioService;

    RegistroController(DistritoRepository distritoRepository, ServicioRepository servicioRepository,
            UsuarioService usuarioService, FreelancerService freelancerService) {
        this.distritoRepository = distritoRepository;
        this.servicioRepository = servicioRepository;
        this.usuarioService = usuarioService;
        this.freelancerService = freelancerService;
    }

    @GetMapping
    public String getMethodName(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("freelancer", new Freelancer());
        model.addAttribute("distritos", distritoRepository.findAll());
        model.addAttribute("servicios", servicioRepository.findAll());

        return "registro";
    }

    @PostMapping("/guardarFreelancer")
    public String guardarFreelancer(@Valid @ModelAttribute Freelancer freelancer, BindingResult result, Model model,
            HttpServletRequest request) throws IOException {
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile fotoPerfilFile = multipartRequest.getFile("fotoPerfilFile");
            if (fotoPerfilFile != null && !fotoPerfilFile.isEmpty()) {
                freelancer.setFotoPerfil(fotoPerfilFile.getBytes());
            }
            List<MultipartFile> documentosFiles = multipartRequest.getFiles("documentosFiles");
            for (MultipartFile file : documentosFiles) {
                if (!file.isEmpty()) {
                    Documento doc = new Documento();
                    doc.setNombre(file.getOriginalFilename());
                    doc.setDatos(file.getBytes());
                    freelancer.addDocumento(doc);
                }
            }
        }
        if (result.hasErrors()) {
            model.addAttribute("distritos", distritoRepository.findAll());
            model.addAttribute("servicios", servicioRepository.findAll());
            model.addAttribute("usuario", new Usuario());
            model.addAttribute("tipoRegistro", "freelancer");
            return "registro";
        }
        // Resolver el distrito correctamente si fue enviado como id por el formulario
        if (freelancer.getDistrito() != null && freelancer.getDistrito().getId() != null) {
            freelancer.setDistrito(distritoRepository.findById(freelancer.getDistrito().getId()).orElse(null));
        }

        freelancerService.guardarFreelancer(freelancer);
        // Después del registro de freelancer, llevar al login mostrando el formulario de freelancer
        return "redirect:/login?tipo=freelancer";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("distritos", distritoRepository.findAll());
            model.addAttribute("servicios", servicioRepository.findAll());
            model.addAttribute("freelancer", new Freelancer());
            model.addAttribute("tipoRegistro", "usuario");
            return "registro";
        }
        // Resolver el distrito si fue enviado como id
        if (usuario.getDistrito() != null && usuario.getDistrito().getId() != null) {
            usuario.setDistrito(distritoRepository.findById(usuario.getDistrito().getId()).orElse(null));
        }

        usuarioService.guardarUsuario(usuario);
        // Después del registro, llevar al login y mostrar el formulario de usuario
        return "redirect:/login?tipo=usuario";
    }
}