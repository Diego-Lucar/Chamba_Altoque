package com.project.sistema.chamba_altoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/modulo_cliente") // Ruta base corregida con guion bajo (_) según tu estructura
public class ModuloClienteController {

    // --- TUS MÉTODOS ANTERIORES ---

    @GetMapping("/dashboard")
    public String dashboard() {
        return "modulo_cliente/dashboard"; // Ajusta este retorno si es necesario según tu proyecto
    }

    @GetMapping("/historial")
    public String historial() {
        return "modulo_cliente/historial"; // Ajusta este retorno si es necesario según tu proyecto
    }


    // --- NUEVO MÉTODO PARA TU APARTADO DE FIGMA ---
    
    @GetMapping("/solicitud")
    public String mostrarFormularioPrueba() {
        // Como tu archivo "solicitud.html" está en la raíz de templates, lo llamamos directo.
        // Si tu compañero lo guardó dentro de una carpeta, usa "modulo_cliente/solicitud"
        return "formulario-solicitud"; 
    }
}