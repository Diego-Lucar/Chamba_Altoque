package com.project.sistema.chamba_altoque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sistema.chamba_altoque.entities.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    Optional<Usuario> findByCorreo(String correo);
}
