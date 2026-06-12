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

    public List<Freelancer> listarFreelancers() {
        return freelancerRepository.findAll();
    }

    public Freelancer guardarFreelancer(Freelancer freelancer) {
        return freelancerRepository.save(freelancer);
    }

    public void eliminarFreelancer(Integer id){
        freelancerRepository.deleteById(id);
    }

}
