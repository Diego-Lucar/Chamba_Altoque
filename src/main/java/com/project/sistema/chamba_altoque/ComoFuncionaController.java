package com.project.sistema.chamba_altoque;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComoFuncionaController {

    @GetMapping("/como-funciona")
    public String mostrarComoFunciona() {
        // Esto buscará el archivo como-funciona.html dentro de la carpeta templates
        return "como-funciona"; 
    }
}