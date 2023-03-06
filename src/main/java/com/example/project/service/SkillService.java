package com.example.project.service;

import com.example.project.model.Skill;
import com.example.project.repository.SkillRepository;
import com.example.project.repository.impl.SkillRepositoryImpl;

import java.util.List;

public class SkillService {
    private final SkillRepository skillRepositoryImpl = new SkillRepositoryImpl();
    public List<Skill> getAllSkills(){
        return skillRepositoryImpl.getAll();
    }
    public Skill getSkillByID(long id){
        return skillRepositoryImpl.getById(id);
    }
    public Skill saveSkill(Skill skill){
        return skillRepositoryImpl.save(skill);
    }
    public void deleteSkillByID(Long id){
        skillRepositoryImpl.deleteById(id);
    }
    public Skill updateSkill(Skill skill){
        return skillRepositoryImpl.update(skill);
    }
}
