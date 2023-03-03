package com.example.project;

import com.example.project.model.Developer;
import com.example.project.model.Skill;
import com.example.project.repository.SkillRepository;
import com.example.project.repository.impl.SkillRepositoryImpl;
import com.example.project.view.CommonView;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//        Skill skill = null;
//        SkillRepository skillRepository = new SkillRepositoryImpl();
//        skillRepository.save(skill);

        CommonView commonView = new CommonView();
        commonView.run();

//        Set<Skill> skills = new HashSet<>();
//        skills.add(null);
//        Developer developer = new Developer("test",skills);
//
//        if (developer.getSkills().stream().findFirst().isPresent()){
//            System.out.println("1");
//        }else System.out.println("0");

    }
//пофиксить метод update для аккаунта, заменить логику удаления/сохранение на обновление существующего - 20%
//пофиксить метод update для девелопера, заменить логику удаления/сохранение на обновление существующего - ?
//доделать девелопер вьюху метод апдейт - 30%
//пофиксить статус аккаунт метод, который при неправильном выборе кидает ошибку - 85%
    //developer:
    // getLastAddedDeveloper - ok
    // delete - ok
    // getById - ok
    // getAll - ok
    // extract - ok
    // save - ok
    // update - ?

}
