package com.lostresv.dao;

import com.lostresv.model.Administrator;

import java.sql.*;

public class AdministratorDAO {

    private Connection connection;

    public AdministratorDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Administrator admin) throws SQLException {
        String sql = "INSERT INTO administrator (user_id, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, admin.getId());
            stmt.setString(2, admin.getUsername());
            stmt.setString(3, admin.getPassword());
            stmt.executeUpdate();
        }
    }

    public Administrator findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM administrator INNER JOIN app_user ON administrator.user_id = app_user.id WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Administrator(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDate("creation_date"),
                    null,
                    rs.getString("username"),
                    rs.getString("password")
                );
            }
        }
        return null;
    }
}
