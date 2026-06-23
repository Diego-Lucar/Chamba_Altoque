package com.project.sistema.chamba_altoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sistema.chamba_altoque.entities.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    public List<Servicio> findAll();
}
