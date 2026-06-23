package com.project.sistema.chamba_altoque.repository;

import com.project.sistema.chamba_altoque.entities.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface FreelancerRepository extends JpaRepository<Freelancer, Integer> {

    Optional<Freelancer> findByCorreo(String correo);

    // CONSULTA JPQL: Une Freelancer con su Distrito y busca por coincidencia de texto
    @Query("SELECT f FROM Freelancer f JOIN f.distrito d " +
           "WHERE LOWER(d.nombre) LIKE LOWER(CONCAT('%', :distrito, '%'))")
    List<Freelancer> buscarPorDistrito(@Param("distrito") String distrito);
}