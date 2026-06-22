package com.project.sistema.chamba_altoque.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.sistema.chamba_altoque.entities.Usuario;
import com.project.sistema.chamba_altoque.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public void eliminarUsuario(Integer id){
        usuarioRepository.deleteById(id);
    }

    public Integer contarUsuarios() {
        return (int) usuarioRepository.count();
    }
}
