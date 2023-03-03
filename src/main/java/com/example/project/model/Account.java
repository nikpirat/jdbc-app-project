package com.example.project.model;

public class Account {
    private long id;
    private long developer_id;
    private String username;
    private AccountStatus accountStatus;

    public Account(long developer_id, String username, AccountStatus accountStatus) {
        this.developer_id = developer_id;
        this.username = username;
        this.accountStatus = accountStatus;
    }

    public Account(long id, long developer_id, String username, AccountStatus accountStatus) {
        this.id = id;
        this.developer_id = developer_id;
        this.username = username;
        this.accountStatus = accountStatus;
    }

    public Account(long developer_id, String username) {
        this.developer_id = developer_id;
        this.username = username;
    }

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(long developer_id) {
        this.developer_id = developer_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", developer_id=" + developer_id +
                ", username='" + username + '\'' +
                ", accountStatus=" + accountStatus +
                "}\n";
    }
}
