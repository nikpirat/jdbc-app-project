package com.example.project.service;


import com.example.project.model.Skill;
import com.example.project.repository.SkillRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService;
    private Skill skill;

    @Test
    public void saveSkill(){
        skill = new Skill(1,"Java");
        when(skillRepository.save(any(Skill.class))).thenReturn(skill);

        Skill createSkill = skillService.saveSkill(skill);

        assertEquals(createSkill,skill);
        verify(skillRepository).save(skill);
    }

    @Test
    public void getSkillById(){
        skill = new Skill(1,"Java");
        skillService.saveSkill(skill);
        when(skillRepository.getById(1L)).thenReturn(skill);

        Skill expected = skillService.getSkillByID(skill.getId());

        assertEquals(expected, skill);
        verify(skillRepository).getById(skill.getId());
    }

    @Test
    public void getAllSkills(){
        List<Skill>skillList = new ArrayList<>();
        skillList.add(new Skill(1,"Java"));
        skillList.add(new Skill(2,"MySQL"));
        when(skillRepository.getAll()).thenReturn(skillList);

        List<Skill> expected = skillService.getAllSkills();

        assertEquals(expected, skillList);
        verify(skillRepository).getAll();
    }

    @Test
    public void updateSkill(){
        skill = new Skill(1,"JavaScript");
        when(skillRepository.getById(skill.getId())).thenReturn(skill);

        skillService.updateSkill(skill);

        verify(skillRepository).update(skill);
    }

    @Test
    public void deleteSkillById(){
        skill = new Skill(1, "Java");
        when(skillRepository.getById(skill.getId())).thenReturn(skill);

        skillService.deleteSkillByID(skill.getId());

        verify(skillRepository).deleteById(skill.getId());
    }
}