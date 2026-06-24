package com.project.sistema.chamba_altoque.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.project.sistema.chamba_altoque.entities.Freelancer;
import java.util.List;
import java.util.Optional;

public interface FreelancerRepository extends JpaRepository<Freelancer, Integer> {

    Optional<Freelancer> findByCorreo(String correo);

    // Se agregó para el login
    @Query("SELECT f FROM Freelancer f JOIN f.distrito d " +
            "WHERE LOWER(d.nombre) LIKE LOWER(CONCAT('%', :distrito, '%'))")
    List<Freelancer> buscarPorDistrito(@Param("distrito") String distrito);
    
    // se añadio el query
    @Query("SELECT DISTINCT f FROM Freelancer f LEFT JOIN f.categorias c " +
        "WHERE :keyword IS NULL OR :keyword = '' " +
        "OR LOWER(f.nombre) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
        "OR LOWER(f.distrito.nombre) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
        "OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Freelancer> buscarPorKeyword(@Param("keyword") String keyword);
    
}