package com.lostresv.dao;

import com.lostresv.factory.UserFactorySelector;
import com.lostresv.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public int insert(User user) throws SQLException {
        String sql = "INSERT INTO app_user (name, creation_date, user_type) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getName());
            stmt.setDate(2, new java.sql.Date(user.getCreationDate().getTime()));
            stmt.setString(3, user.getUserType());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // new user ID
            }
        }
        return -1;
    }

    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM app_user WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return UserFactorySelector.createUserByType(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("creation_date"),
                        rs.getString("user_type")
                );
            }
        }
        return null;
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM app_user";
        List<User> users = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                users.add(UserFactorySelector.createUserByType(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("creation_date"),
                        rs.getString("user_type")
                ));
            }
        }
        return users;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM app_user WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
