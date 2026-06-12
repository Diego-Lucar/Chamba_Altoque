package com.project.sistema.chamba_altoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sistema.chamba_altoque.entities.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
}
