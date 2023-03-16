package com.example.project.repository.impl;

import com.example.project.constants.Constants;
import com.example.project.model.Developer;
import com.example.project.model.Skill;
import com.example.project.repository.AccountRepository;
import com.example.project.repository.ConnectionPool;
import com.example.project.repository.DeveloperRepository;

import java.sql.*;
import java.util.*;

public class DeveloperRepositoryImpl implements DeveloperRepository {
    private static final AccountRepository accountRepositoryImpl = new AccountRepositoryImpl();

    @Override
    public List<Developer> getAll() {
        List<Developer> developers = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_GET_ALL_DEVELOPERS, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Developer developer = extractDeveloper(rs);
                developers.add(developer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return developers;
    }
    @Override
    public Developer getById(Long id) {
        Developer developer = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_GET_DEVELOPER_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) developer = extractDeveloper(rs);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return developer;
    }
    @Override
    public long getLastAddedDeveloper() {
        long developer_id = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_GET_LAST_ADDED_DEVELOPER)) {
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) developer_id = rs.getLong("developer_id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return developer_id;
    }
    @Override
    public Developer save(Developer developer) {
        try (Connection connection = ConnectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(Constants.SQL_CREATE_DEVELOPER)) {
                ps.setString(1, developer.getName());
                ps.executeUpdate();
            }
            if (developer.getSkills().stream().findFirst().isPresent()){
                //todo
            }
            try (PreparedStatement ps = connection.prepareStatement(Constants.SQL_CREATE_DEVELOPER_SKILLS)) {
                for (Skill skill : developer.getSkills()) {
                    ps.setLong(1, getLastAddedDeveloper());
                    ps.setLong(2, skill.getId());
                    ps.addBatch();
                }
                ps.executeBatch();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return developer;
    }
    @Override
    public Developer update(Developer developer) {
        try(Connection connection = ConnectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(Constants.SQL_UPDATE_DEVELOPER)){
                ps.setString(1,developer.getName());
                ps.setLong(2, developer.getId());
                ps.executeUpdate();
            }
            try(PreparedStatement ps = connection.prepareStatement(Constants.SQL_UPDATE_ACCOUNT)){
                ps.setLong(1, developer.getId());
                ps.setString(2, developer.getAccount().getUsername());
                ps.setString(3, developer.getAccount().getAccountStatus().toString());
                ps.setLong(4, developer.getAccount().getId());
                ps.executeUpdate();
            }
            try(PreparedStatement ps = connection.prepareStatement(Constants.SQL_CREATE_DEVELOPER_SKILLS)){
                deleteDeveloperSkills(developer,connection);

                for (Skill skill : developer.getSkills()) {
                    ps.setLong(1, getLastAddedDeveloper());
                    ps.setLong(2, skill.getId());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            catch (SQLException e){
                try{
                    connection.rollback();
                    System.err.println("Something went wrong in developer Update method, processing rollback option...");
                } catch (SQLException throwables) {
                    System.err.println("There was an error making a rollback");
                    throwables.printStackTrace();
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return developer;
    }
    @Override
    public void deleteById(Long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(Constants.SQL_DELETE_DEVELOPER_SKILLS)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = connection.prepareStatement(Constants.SQL_DELETE_ACCOUNT_BY_DEV_ID)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = connection.prepareStatement(Constants.SQL_DELETE_DEVELOPER)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private Developer extractDeveloper(ResultSet rs) throws SQLException {
        Developer developer = new Developer();
        developer.setId(rs.getLong("developer_id"));
        developer.setName(rs.getString("name"));
        developer.setAccount(accountRepositoryImpl.getById(rs.getLong("account_id")));

        Set<Skill> skills = new HashSet<>();
        do{
            Skill skill = new Skill(rs.getLong("skill_id"), rs.getString("skill_name"));
            skills.add(skill);
        }while (rs.next() && rs.getLong("developer_id")==developer.getId());
        developer.setSkills(skills);
        rs.previous();

        return developer;
    }
    private Developer deleteDeveloperSkills(Developer developer, Connection connection){
        try(PreparedStatement ps = connection.prepareStatement(Constants.SQL_DELETE_DEVELOPER_SKILLS)){
            ps.setLong(1,developer.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return developer;
    }
}
