package com.example.project.service;

import com.example.project.model.Account;
import com.example.project.repository.AccountRepository;
import com.example.project.repository.impl.AccountRepositoryImpl;

import java.util.List;

public class AccountService {
    private final AccountRepository accountRepositoryImpl = new AccountRepositoryImpl();

    public List<Account> getAllAccounts() {
        return accountRepositoryImpl.getAll();
    }
    public Account getAccountByID(Long id){
        return accountRepositoryImpl.getById(id);
    }
    public Account saveAccount(Account account) {
        return accountRepositoryImpl.save(account);
    }
    public void deleteAccountByID(Long id) {
        accountRepositoryImpl.deleteById(id);
    }
    public Account updateAccount(Account account) {
        return accountRepositoryImpl.update(account);
    }
}
