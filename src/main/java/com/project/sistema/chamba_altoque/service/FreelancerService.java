package com.project.sistema.chamba_altoque.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.sistema.chamba_altoque.entities.Documento;
import com.project.sistema.chamba_altoque.entities.Freelancer;
import com.project.sistema.chamba_altoque.repository.FreelancerRepository;

@Service
public class FreelancerService {

    private final FreelancerRepository freelancerRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    FreelancerService(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }

    public void guardarFreelancer(Freelancer freelancer, MultipartFile fotoPerfilFile, List<MultipartFile> documentosFiles)
            throws IOException {

        if (fotoPerfilFile != null && !fotoPerfilFile.isEmpty()) {
            freelancer.setFotoPerfil(fotoPerfilFile.getBytes());
        }

        if (documentosFiles != null) {
            for (MultipartFile file : documentosFiles) {
                if (file == null || file.isEmpty()) {
                    continue;
                }
                Documento documento = new Documento();
                documento.setNombre(file.getOriginalFilename());
                documento.setDatos(file.getBytes());
                freelancer.addDocumento(documento);
            }
        }

        // Codificar la contraseña antes de guardar
        if (freelancer.getPassword() != null && !freelancer.getPassword().isEmpty()) {
            freelancer.setPassword(passwordEncoder.encode(freelancer.getPassword()));
        }

        freelancerRepository.save(freelancer);
    }

    // Sobrecarga para cuando el controlador ya procesó los archivos y documentos
    public void guardarFreelancer(Freelancer freelancer) {
        if (freelancer.getPassword() != null && !freelancer.getPassword().isEmpty()) {
            freelancer.setPassword(passwordEncoder.encode(freelancer.getPassword()));
        }
        freelancerRepository.save(freelancer);
    }

    /**
     * Valida las credenciales de un freelancer por correo y contraseña.
     * Devuelve el Freelancer si coinciden, o Optional.empty() si no.
     */
    public Optional<Freelancer> autenticar(String correo, String password) {
        return freelancerRepository.findByCorreo(correo)
            .filter(freelancer -> freelancer.getPassword() != null && passwordEncoder.matches(password, freelancer.getPassword()));
    }

    public List<Freelancer> listarFreelancers() {
        return freelancerRepository.findAll();
    }

    public long contarFreelancers() {
        return freelancerRepository.count();
    }

    public Freelancer obtenerFreelancerPorId(Integer id) {
        return freelancerRepository.findById(id).orElse(null);
    }
}
