package com.example.project.controller;



import com.example.project.model.Developer;
import com.example.project.service.DeveloperService;

import java.util.List;

public class DeveloperController {
    private final DeveloperService developerService = new DeveloperService();

    public List<Developer> getAllDevelopers() {
        return developerService.getAllDevelopers();
    }
    public Developer getDeveloperByID(Long id){
        return developerService.getDeveloperByID(id);
    }
    public Developer saveDeveloper(Developer developer) {
        return developerService.saveDeveloper(developer);
    }
    public void deleteDeveloperByID(Long id) {
        developerService.deleteDeveloperByID(id);
    }
    public Developer updateDeveloper(Developer developer) {
        return developerService.updateDeveloper(developer);
    }
    public long getLastAddedDeveloper(){return developerService.getLastAddedDeveloper();}
}
