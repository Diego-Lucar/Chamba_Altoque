package com.project.sistema.chamba_altoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sistema.chamba_altoque.entities.Distrito;

public interface DistritoRepository extends JpaRepository<Distrito, Long> {
    
    public List<Distrito> findAll();
}
