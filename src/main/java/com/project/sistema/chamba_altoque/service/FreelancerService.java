package com.project.sistema.chamba_altoque.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.sistema.chamba_altoque.entities.Freelancer;
import com.project.sistema.chamba_altoque.repository.FreelancerRepository;

@Service
public class FreelancerService {
    private final FreelancerRepository freelancerRepository;

    FreelancerService(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }

    public Freelancer obtenerFreelancerPorId(Integer id) {
        return freelancerRepository.findById(id).orElse(null);
    }

    public void guardarFreelancer(Freelancer f) {
        freelancerRepository.save(f);
    }

    public List<Freelancer> listarFreelancers(){
        return freelancerRepository.findAll();
    }

    public Integer contarFreelancers() {
        return (int) freelancerRepository.count();
    }
    //se añadió esto
    public List<Freelancer> buscarFreelancers(String keyword) {
        return freelancerRepository.buscarPorKeyword(keyword);
    }
}
