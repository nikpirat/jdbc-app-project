package com.example.project.service;

import com.example.project.model.Account;
import com.example.project.model.AccountStatus;
import com.example.project.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;
    private Account account;


    @Test
    public void saveAccount(){
        account = new Account(1,"Test", AccountStatus.ACTIVE);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createAccount = accountService.saveAccount(account);

        assertEquals(createAccount, account);
        verify(accountRepository).save(account);
    }

    @Test
    public void getAccountById(){
        account = new Account(1,1,"Test", AccountStatus.ACTIVE);
        accountService.saveAccount(account);
        when(accountRepository.getById(1L)).thenReturn(account);

        Account expected = accountService.getAccountByID(account.getId());

        assertEquals(expected, account);
        verify(accountRepository).getById(account.getId());
    }

    @Test
    public void getAllAccounts(){
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account(1,"Test", AccountStatus.ACTIVE));
        accountList.add(new Account(2,"Test2", AccountStatus.ACTIVE));
        when(accountRepository.getAll()).thenReturn(accountList);

        List<Account> expected = accountService.getAllAccounts();

        assertEquals(expected, accountList);
        verify(accountRepository).getAll();
    }

    @Test
    public void updateAccount(){
        account = new Account(1,"Test", AccountStatus.ACTIVE);
        when(accountRepository.getById(1L)).thenReturn(account);

        accountService.updateAccount(account);

        verify(accountRepository).update(account);
    }

    @Test
    public void deleteAccountById(){
        account = new Account(1,"Test", AccountStatus.ACTIVE);
        when(accountRepository.getById(1L)).thenReturn(account);

        accountService.deleteAccountByID(account.getId());

        verify(accountRepository).deleteById(account.getId());
    }
}
