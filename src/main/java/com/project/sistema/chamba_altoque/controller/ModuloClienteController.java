package com.project.sistema.chamba_altoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModuloClienteController {

    // @GetMapping("/dashboard")
    // public String dashboard() {
    //     return "modulo_cliente/dashboard";
    // }

    // @GetMapping("/historial")
    // public String historial() {
    //     return "modulo_cliente/historial";
    // }

    @GetMapping("/solicitud")
    public String mostrarFormularioPrueba() {

        return "formulario-solicitud";
    }
}