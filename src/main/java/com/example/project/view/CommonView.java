package com.example.project.view;

import java.util.Scanner;

public class CommonView {
    private final SkillView skillView = new SkillView();
    private final AccountView accountView = new AccountView();
    private final DeveloperView developerView = new DeveloperView();

    private static final String text = "Select item: \n1) Developer menu \n2) Skill menu \n3) Account menu \n4) Exit program";
    private static final String error = "Please, enter a valid number";

    public void run() {
        Scanner in = new Scanner(System.in);
        int choice;
        do {
            System.out.println(text);
            switch (choice = in.nextInt()) {
                case 1:
                    developerView.showDeveloperMenu();
                    break;
                case 2:
                    skillView.showSkillMenu();
                    break;
                case 3:
                    accountView.showAccountMenu();
                    break;
                case 4:
                    break;
                default:
                    System.out.println(error);
                    break;
            }
        } while (choice != 4);
    }
}
