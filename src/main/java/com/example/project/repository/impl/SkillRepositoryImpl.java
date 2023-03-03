package com.example.project.repository.impl;

import com.example.project.constants.Constants;
import com.example.project.model.Skill;
import com.example.project.repository.ConnectionPool;
import com.example.project.repository.SkillRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SkillRepositoryImpl implements SkillRepository {

    @Override
    public List<Skill> getAll() {
        List<Skill> skills = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_GET_ALL_SKILLS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Skill skill = extractSkill(rs);
                skills.add(skill);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return skills;
    }

    @Override
    public Skill getById(Long id) {
        Skill skill = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_GET_SKILL_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) skill = extractSkill(rs);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return skill;
    }

    @Override
    public Skill save(Skill skill) {

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_CREATE_SKILL)) {
            ps.setString(1, skill.getName());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_UPDATE_SKILL)) {
            ps.setString(1, skill.getName());
            ps.setLong(2, skill.getId());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return skill;
    }

    @Override
    public void deleteById(Long id) {

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_DELETE_SKILL)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Skill extractSkill(ResultSet rs) throws SQLException {
        Skill skill = new Skill();
        skill.setId(rs.getLong("skill_id"));
        skill.setName(rs.getString("skill_name"));
        return skill;
    }
}
