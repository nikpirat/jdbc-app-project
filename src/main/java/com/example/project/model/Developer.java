package com.example.project.model;

import java.util.Set;

public class Developer {
    private long id;
    private String name;
    private Account account;
    private Set<Skill> skills;

    public Developer(long id, String name, Account account, Set<Skill> skills) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.skills = skills;
    }

    public Developer(String name, Account account, Set<Skill> skills) {
        this.name = name;
        this.account = account;
        this.skills = skills;
    }

    public Developer(String name, Set<Skill> skills) {
        this.name = name;
        this.skills = skills;
    }

    public Developer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account=" + account +
                ", skills=" + skills +
                "}\n";
    }
}
