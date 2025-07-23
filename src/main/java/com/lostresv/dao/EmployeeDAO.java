package com.lostresv.dao;

import com.lostresv.model.Employee;

import java.sql.*;

public class EmployeeDAO {

    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (user_id, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employee.getId());
            stmt.setString(2, employee.getUsername());
            stmt.setString(3, employee.getPassword());
            stmt.executeUpdate();
        }
    }

    public Employee findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM employee INNER JOIN app_user ON employee.user_id = app_user.id WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(
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
