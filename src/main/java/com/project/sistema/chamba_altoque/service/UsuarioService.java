package com.project.sistema.chamba_altoque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.sistema.chamba_altoque.entities.Usuario;
import com.project.sistema.chamba_altoque.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void guardarUsuario(Usuario usuario) {
        // Almacenar la contraseña de forma segura usando BCrypt
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public void eliminarUsuario(Integer id){
        usuarioRepository.deleteById(id);
    }

    public long contarUsuarios() {
        return usuarioRepository.count();
    }

    /**
     * Valida las credenciales de un usuario por correo y contraseña.
     * Devuelve el Usuario si coinciden, o Optional.empty() si no.
     */
    public Optional<Usuario> autenticar(String correo, String password) {
        return usuarioRepository.findByCorreo(correo)
            .filter(usuario -> usuario.getPassword() != null && passwordEncoder.matches(password, usuario.getPassword()));
    }
}
