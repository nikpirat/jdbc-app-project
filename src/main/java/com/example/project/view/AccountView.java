package com.example.project.view;

import com.example.project.controller.AccountController;
import com.example.project.model.Account;
import com.example.project.model.AccountStatus;

import java.util.Scanner;

public class AccountView {
    private final AccountController accountController = new AccountController();
    private static final String start = "Press 1 to find account\nPress 2 to get all accounts\nPress 3 to delete account\n" +
            "Press 4 to create account\nPress 5 to update account\nPress 6 to return to main menu";
    private static final String startUpdate = "Select item:\n1) Change user's ID\n2) Change username\n3) Change account status\n4)Return to account menu";
    private static final String end = "Successfully";
    private static final String text1 = "Enter account's ID: ";
    private static final String text2 = "Enter user's ID: ";
    private static final String update_userID = "Enter new user's ID: ";
    private static final String text3 = "Enter username: ";
    private static final String update_username = "Enter new username: ";
    private static final String text4 = "Enter account's status:\n1) Active\n2) Banned\n3) Deleted";
    private static final String update_accountStatus = "Enter new account status:\n1) Active\n2) Banned\n3) Deleted";
    private static final String accountNotFound = "Account with such ID not found in database.\n";
    private static final String error = "Please, enter valid number\n";



    protected static AccountStatus chooseAccountStatus(int choice) {
        AccountStatus status = null;
        switch (choice) {
            case 1:
                status = AccountStatus.ACTIVE;
                break;
            case 2:
                status = AccountStatus.BANNED;
                break;
            case 3:
                status = AccountStatus.DELETED;
                break;
            default:
//                System.out.println(error);
                break;
        }
        return status;
    }

    public void showAccountMenu() {
        Scanner in = new Scanner(System.in);
        int choice;

        do {
            System.out.println(start);
            switch (choice = in.nextInt()) {
                case 1: /** GETTING ACCOUNT BY ID **/
                    System.out.println(text1);
                    Account account = accountController.getAccountByID(in.nextLong());
                    System.out.println((account == null ? accountNotFound : account) + "\n");
                    break;
                case 2: /** GETTING ALL ACCOUNTS **/
                    System.out.println(accountController.getAllAccounts() + "\n");
                    break;
                case 3: /** DELETING ACCOUNT **/
                    System.out.println(text1);
                    try {
                        accountController.deleteAccountByID(in.nextLong());
                        System.out.println(end);
                    } catch (Exception e) {
                        System.out.println(accountNotFound);
                    }
                    break;
                case 4: /** CREATING NEW ACCOUNT **/
                    System.out.println(text2);
                    long userId = in.nextLong();
                    System.out.println(text3);
                    String username = in.next();
                    System.out.println(text4);
                    AccountStatus status;
                    do {
                        status = chooseAccountStatus(in.nextInt());
                        if (status==null) System.out.println("Please enter valid number");
                    } while (status==null);

                    accountController.saveAccount(new Account(userId, username, status));
                    break;
                case 5: /** UPDATING ACCOUNT **/
                    System.out.println(text1);
                    Account accountToUpdate = accountController.getAccountByID(in.nextLong());
                    if (accountToUpdate == null) {
                        System.out.println(accountNotFound);
                        break;
                    }
                    int updateChoice;
                    do {
                        System.out.println(startUpdate);
                        switch (updateChoice = in.nextInt()) {
                            case 1: /** UPDATING ACCOUNT USER ID **/
                                System.out.println(update_userID);
                                accountToUpdate.setDeveloper_id(in.nextLong());
                                accountController.updateAccount(accountToUpdate);
                                break;
                            case 2: /** UPDATING ACCOUNT USERNAME **/
                                System.out.println(update_username);
                                accountToUpdate.setUsername(in.next());
                                accountController.updateAccount(accountToUpdate);
                                break;
                            case 3: /** UPDATING ACCOUNT STATUS **/
                                AccountStatus updateStatus;
                                do {
                                    System.out.println(update_accountStatus);
                                    updateStatus = chooseAccountStatus(in.nextInt());
                                    if (updateStatus==null) System.out.println(error);
                                } while (updateStatus==null);

                                accountToUpdate.setAccountStatus(updateStatus);
                                accountController.updateAccount(accountToUpdate);
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println(error);
                                break;
                        }
                    } while (updateChoice != 4);
                case 6:
                    break;
                default:
                    System.out.println(error);
                    break;
            }
        } while (choice != 6);
    }
}
