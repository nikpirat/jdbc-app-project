package com.example.project.service;

import com.example.project.model.Developer;
import com.example.project.repository.DeveloperRepository;
import com.example.project.repository.impl.DeveloperRepositoryImpl;

import java.util.List;

public class DeveloperService {
    private final DeveloperRepository developerRepositoryImpl = new DeveloperRepositoryImpl();

    public List<Developer> getAllDevelopers() {
        return developerRepositoryImpl.getAll();
    }
    public Developer getDeveloperByID(Long id){
        return developerRepositoryImpl.getById(id);
    }
    public Developer saveDeveloper(Developer developer) {
        return developerRepositoryImpl.save(developer);
    }
    public void deleteDeveloperByID(Long id) {
        developerRepositoryImpl.deleteById(id);
    }
    public Developer updateDeveloper(Developer developer) {
        return developerRepositoryImpl.update(developer);
    }
    public long getLastAddedDeveloper(){return developerRepositoryImpl.getLastAddedDeveloper();}
}
