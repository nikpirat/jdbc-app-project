package com.example.project.repository.impl;

import com.example.project.constants.Constants;
import com.example.project.model.Account;
import com.example.project.model.AccountStatus;
import com.example.project.repository.AccountRepository;
import com.example.project.repository.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_GET_ALL_ACCOUNTS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = extractAccount(rs);
                accounts.add(account);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    @Override
    public Account getById(Long id) {
        Account account = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_GET_ACCOUNT_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) account = extractAccount(rs);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    public Account save(Account account) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_CREATE_ACCOUNT)) {
            ps.setLong(1, account.getDeveloper_id());
            ps.setString(2, account.getUsername());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    public Account update(Account account) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_UPDATE_ACCOUNT)) {
            ps.setLong(1, account.getDeveloper_id());
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getAccountStatus().toString());
            ps.setLong(4, account.getId());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_DELETE_ACCOUNT)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Account extractAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getLong("account_id"));
        account.setDeveloper_id(rs.getLong("developer_id"));
        account.setUsername(rs.getString("username"));
        account.setAccountStatus(AccountStatus.valueOf(rs.getString("account_status")));
        return account;
    }
}
