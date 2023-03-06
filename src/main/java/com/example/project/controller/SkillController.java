package com.example.project.controller;

import com.example.project.model.Skill;
import com.example.project.repository.impl.SkillRepositoryImpl;
import com.example.project.service.SkillService;

import java.util.List;

public class SkillController {
    private final SkillService skillService = new SkillService();
    public List<Skill> getAllSkills(){
        return skillService.getAllSkills();
    }
    public Skill getSkillByID(long id){
        return skillService.getSkillByID(id);
    }
    public Skill saveSkill(Skill skill){
        return skillService.saveSkill(skill);
    }
    public void deleteSkillByID(Long id){
        skillService.deleteSkillByID(id);
    }
    public Skill updateSkill(Skill skill){
        return skillService.updateSkill(skill);
    }
}
