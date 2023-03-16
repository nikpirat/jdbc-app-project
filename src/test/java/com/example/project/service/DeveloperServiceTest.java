package com.example.project.service;

import com.example.project.model.Account;
import com.example.project.model.AccountStatus;
import com.example.project.model.Developer;
import com.example.project.model.Skill;
import com.example.project.repository.DeveloperRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeveloperServiceTest {

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private DeveloperService developerService;
    private Developer developer;
    private Developer developer1;

    @Test
    public void saveDeveloper(){
        Account account = new Account(1,"test", AccountStatus.ACTIVE);
        Set<Skill> skillSet = new HashSet<Skill>(){{
            add(new Skill(1,"Java"));
            add(new Skill(2,"MySQL"));
        }};
        developer = new Developer(1,"test",account,skillSet);
        when(developerRepository.save(any(Developer.class))).thenReturn(developer);

        Developer expected = developerService.saveDeveloper(developer);

        assertEquals(expected,developer);
    }

    @Test
    public void getDeveloperById(){
        Account account = new Account(1,"test", AccountStatus.ACTIVE);
        Set<Skill> skillSet = new HashSet<Skill>(){{
            add(new Skill(1,"Java"));
            add(new Skill(2,"MySQL"));
        }};
        developer = new Developer(1,"test",account,skillSet);
        when(developerRepository.getById(1L)).thenReturn(developer);

        Developer expected = developerService.getDeveloperByID(developer.getId());

        assertEquals(expected, developer);
        verify(developerRepository).getById(developer.getId());
    }

    @Test
    public void getAllDevelopers(){
        Account account = new Account(1,"test", AccountStatus.ACTIVE);
        Set<Skill> skillSet = new HashSet<Skill>(){{
            add(new Skill(1,"Java"));
            add(new Skill(2,"MySQL"));
        }};
        developer = new Developer(1,"test",account,skillSet);
        Account account1 = new Account(2,"test2", AccountStatus.ACTIVE);
        Set<Skill> skillSet1 = new HashSet<Skill>(){{
            add(new Skill(1,"Java"));
            add(new Skill(2,"MySQL"));
        }};
        developer1 = new Developer(2,"test2",account1,skillSet1);
        List<Developer> developerList = new ArrayList<Developer>(){{
            add(developer);
            add(developer1);
        }};
        when(developerRepository.getAll()).thenReturn(developerList);

        List<Developer> expected = developerService.getAllDevelopers();

        assertEquals(expected, developerList);
        verify(developerRepository).getAll();
    }

    @Test
    public void updateDeveloper(){
        Account account = new Account(1,"test", AccountStatus.ACTIVE);
        Set<Skill> skillSet = new HashSet<Skill>(){{
            add(new Skill(1,"Java"));
            add(new Skill(2,"MySQL"));
        }};
        developer = new Developer(1,"test",account,skillSet);
        when(developerRepository.getById(1L)).thenReturn(developer);

        developerService.updateDeveloper(developer);

        verify(developerRepository).update(developer);
    }

    @Test
    public void deleteDeveloper(){
        Account account = new Account(1,"test", AccountStatus.ACTIVE);
        Set<Skill> skillSet = new HashSet<Skill>(){{
            add(new Skill(1,"Java"));
            add(new Skill(2,"MySQL"));
        }};
        developer = new Developer(1,"test",account,skillSet);
        when(developerRepository.getById(1L)).thenReturn(developer);

        developerService.deleteDeveloperByID(developer.getId());

        verify(developerRepository).deleteById(developer.getId());
    }
}
