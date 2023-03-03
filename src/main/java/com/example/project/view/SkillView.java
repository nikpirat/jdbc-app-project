package com.example.project.view;

import com.example.project.controller.SkillController;
import com.example.project.model.Skill;

import java.util.Scanner;

public class SkillView {
    private final SkillController skillController = new SkillController();

    private static final String start = "Press 1 to find skill\nPress 2 to get all skills\n" +
            "Press 3 to delete skill\nPress 4 to create new skill\nPress 5 to update skill\nPress 6 to return to main menu";
    private static final String end = "Successfully";
    private static final String text1 = "Enter skill's ID: ";
    private static final String text2 = "Enter skill's name: ";
    private static final String text3 = "Enter skill's new name: ";
    private static final String skillNotFound = "Skill with such ID not found in database.\n";
    private static final String error = "Please, enter valid number\n";

    public void showSkillMenu(){
        Scanner in = new Scanner(System.in);
        int choice;
        do {
            System.out.println(start);
            switch (choice = in.nextInt()){
                case 1:
                    System.out.println(text1);
                    Skill skill = skillController.getSkillByID(in.nextLong());
                    System.out.println((skill == null ? skillNotFound : skill) + "\n");
                    break;
                case 2:
                    System.out.println(skillController.getAllSkills() + "\n");
                    break;
                case 3:
                    System.out.println(text1);
                    skillController.deleteSkillByID(in.nextLong());
                    break;
                    //TODO try/catch
                case 4:
                    System.out.println(text2);
                    skillController.saveSkill(new Skill(in.next()));
                    break;
                case 5:
                    System.out.println(text1);
                    Skill updatedSkill = skillController.getSkillByID(in.nextLong());
                    if (updatedSkill==null){
                        System.out.println(skillNotFound);
                        break;
                    }
                    System.out.println(text3);
                    updatedSkill.setName(in.next());
                    skillController.updateSkill(updatedSkill);
                    break;
                case 6:
                    break;
                default:
                    System.out.println(error);
                    break;
            }
        } while (choice !=6);
    }
}
