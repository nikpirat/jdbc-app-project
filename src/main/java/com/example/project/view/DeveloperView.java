package com.example.project.view;

import com.example.project.controller.AccountController;
import com.example.project.controller.DeveloperController;
import com.example.project.controller.SkillController;
import com.example.project.model.Account;
import com.example.project.model.Developer;
import com.example.project.model.Skill;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DeveloperView {

    private final DeveloperController developerController = new DeveloperController();
    private final AccountController accountController = new AccountController();
    private final SkillController skillController = new SkillController();
    private static final String start =
            "Press 1 to find developer\nPress 2 to get all developers\nPress 3 to delete developer\n" +
                    "Press 4 to create developer\nPress 5 to update developer\nPress 6 to return to main menu";
    private static final String startUpdate =
            "Select item: \n1) Change developer's name\nP2) Change developer's account username\n" +
                    "3) Change developer's account status\n4) Add new skill\n5) Delete skill\n6) To confirm updates and return to main menu";

    private static final String end = "Successfully";
    private static final String text1 = "Enter developer's ID: ";
    private static final String createName = "Enter developer's name: ";
    private static final String updateName = "Enter new developer's name: ";
    private static final String updateAccountName = "Enter new developer's account username: ";
    private static final String text3 = "Choose developer's skills: To exit menu - Enter 0";
    private static final String developerNotFound = "Developer with such ID not found in database.\n";
    private static final String skillNotFound = "Skill with such ID not found in database.\n";
    private static final String skillAlreadyInSet = "Skill with such ID has already been added. Please choose another one.\n";
    private static final String error = "Please, enter valid number\n";


    public void showDeveloperMenu() {
        Scanner in = new Scanner(System.in);
        int choice;

        do {
            System.out.println(start);
            switch (choice = in.nextInt()) {
                case 1: /** GETTING DEVELOPER BY ID **/
                    System.out.println(text1);
                    Developer developer = developerController.getDeveloperByID(in.nextLong());
                    System.out.println((developer == null ? developerNotFound : developer) + "\n");
                    break;
                case 2: /** GETTING ALL DEVELOPERS **/
                    System.out.println(developerController.getAllDevelopers());
                    break;
                case 3: /** DELETING DEVELOPER **/
                    System.out.println(text1);
                    try {
                        developerController.deleteDeveloperByID(in.nextLong());
                    } catch (Exception e) {
                        System.out.println(developerNotFound);
                        e.printStackTrace();
                    }
                    break;
                case 4: /** CREATING NEW DEVELOPER **/
                    System.out.println(createName);
                    String name = in.next();
                    System.out.println(text3);
                    System.out.println(skillController.getAllSkills());
                    long skillChoice;
                    Set<Skill> skills = new HashSet<>();
                    do {
                        skillChoice = in.nextLong();
                        if (skillChoice != 0) {
                            Skill skill = skillController.getSkillByID(skillChoice);
                            if (skill != null) {
                                if (skills.contains(skill)) {
                                    System.out.println(skillAlreadyInSet);
                                }
                                else skills.add(skill);
                            }
                            else System.out.println(skillNotFound);
                        }
                    } while (skillChoice != 0);
                    try {
                        developerController.saveDeveloper(new Developer(name, skills));
                        accountController.saveAccount(new Account(developerController.getLastAddedDeveloper(), name));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 5: /** UPDATING DEVELOPER **/
                    System.out.println(text1);
                    long developerId = in.nextLong();
                    Developer developerToUpdate = developerController.getDeveloperByID(developerId);
                    if (developerToUpdate == null) {
                        System.out.println(developerNotFound);
                        break;
                    }

                    int updateChoice;
                    do {
                        System.out.println(startUpdate);
                        switch (updateChoice = in.nextInt()) {
                            case 1: /** UPDATING DEVELOPERS NAME **/
                                System.out.println(updateName);
                                developerToUpdate.setName(in.next());
                                break;
                            case 2: /** UPDATING DEVELOPERS ACCOUNT USERNAME **/
                                System.out.println(updateAccountName);
                                developerToUpdate.getAccount().setUsername(in.next());
                                break;
                            case 3: /** UPDATING DEVELOPERS ACCOUNT STATUS **/
                                System.out.println();
                                developerToUpdate.getAccount().setAccountStatus(AccountView.chooseAccountStatus(in.nextInt()));
//                                accountToUpdate.setAccountStatus(AccountView.chooseAccountStatus(in.nextInt()));
                                break;
                            case 4: /** ADDING NEW SKILL TO DEVELOPER **/
                                System.out.println("Enter skill ID to add:\n" + skillController.getAllSkills());
                                Skill skillToAdd = skillController.getSkillByID(in.nextLong());
                                if (skillToAdd == null) {
                                    System.out.println("Skill with such ID not found");
                                    break;
                                }
                                if (developerToUpdate.getSkills().contains(skillToAdd)){
                                    System.out.println("Such skill is already in list");
                                    break;
                                }
                                developerToUpdate.getSkills().add(skillToAdd);
                                break;
                            case 5: /** DELETING SKILL FROM DEVELOPER **/
                                System.out.println("Enter skill ID to delete:\n" + developerToUpdate.getSkills());
                                developerToUpdate.getSkills().removeIf(skill -> skill.getId() == in.nextLong());
                                break;
                            case 6: /** UPDATE DEVELOPER AFTER ALL CHANGES **/
                                developerController.updateDeveloper(developerToUpdate);
                                break;
                            default:
                                System.out.println(error);
                                break;
                        }
                    } while (updateChoice != 6);
                case 6:
                    break;
                default:
                    System.out.println(error);
                    break;
            }
        } while (choice != 6);
    }
}
