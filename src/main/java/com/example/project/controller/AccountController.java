package com.example.project.controller;

import com.example.project.model.Account;
import com.example.project.repository.AccountRepository;
import com.example.project.repository.impl.AccountRepositoryImpl;
import com.example.project.service.AccountService;

import java.util.List;

public class AccountController {
    private final AccountService accountService = new AccountService();

    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    public Account getAccountByID(Long id){
        return accountService.getAccountByID(id);
    }
    public Account saveAccount(Account account) {
        return accountService.saveAccount(account);
    }
    public void deleteAccountByID(Long id) {
        accountService.deleteAccountByID(id);
    }
    public Account updateAccount(Account account) {
        return accountService.updateAccount(account);
    }

}
