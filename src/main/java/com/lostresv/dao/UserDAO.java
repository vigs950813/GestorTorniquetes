// File: com/lostresv/dao/UserDAO.java
package com.lostresv.dao;

import com.lostresv.model.*;
import com.lostresv.util.DatabaseConnection;

import java.sql.*;
import java.util.Date;

public class UserDAO {

    public User authenticate(String username, String password) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // 1. Try administrator
            String adminQuery = "SELECT a.user_id, a.password, u.name, u.creation_date, u.user_type, c.id as card_id, c.uid, c.active, c.issue_date "
                    + "FROM administrator a "
                    + "JOIN app_user u ON a.user_id = u.id "
                    + "LEFT JOIN card c ON u.id = c.user_id "
                    + "WHERE a.username = ? AND a.password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(adminQuery)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return mapAdministrator(rs);
                    }
                }
            }

            // 2. Try employee
            String employeeQuery = "SELECT e.user_id, e.password, u.name, u.creation_date, u.user_type, c.id as card_id, c.uid, c.active, c.issue_date "
                    + "FROM employee e "
                    + "JOIN app_user u ON e.user_id = u.id "
                    + "LEFT JOIN card c ON u.id = c.user_id "
                    + "WHERE e.username = ? AND e.password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(employeeQuery)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return mapEmployee(rs);
                    }
                }
            }
        }

        return null;
    }

    private Administrator mapAdministrator(ResultSet rs) throws SQLException {
        int id = rs.getInt("user_id");
        String name = rs.getString("name");
        Date creationDate = rs.getDate("creation_date");
        String password = rs.getString("password");
        String username = rs.getString("username"); // only works if you include this in SELECT
        Card card = buildCard(rs, id);
        return new Administrator(id, name, creationDate, card, username, password);
    }

    private Employee mapEmployee(ResultSet rs) throws SQLException {
        int id = rs.getInt("user_id");
        String name = rs.getString("name");
        Date creationDate = rs.getDate("creation_date");
        String password = rs.getString("password");
        String username = rs.getString("username"); // only works if you include this in SELECT
        Card card = buildCard(rs, id);
        return new Employee(id, name, creationDate, card, username, password);
    }

    private Card buildCard(ResultSet rs, int userId) throws SQLException {
        int cardId = rs.getInt("card_id");
        if (rs.wasNull()) {
            return null; // No card registered
        }
        String uid = rs.getString("uid");
        boolean active = rs.getBoolean("active");
        Date issueDate = rs.getDate("issue_date");
        return new Card(cardId, uid, active, userId, issueDate);
    }
}
