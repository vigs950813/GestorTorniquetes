package com.lostresv.dao;

import com.lostresv.model.Card;

import java.sql.*;
import java.util.Date;

public class CardDAO {

    private Connection connection;

    public CardDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Card card) throws SQLException {
        String sql = "INSERT INTO card (uid, active, user_id, issue_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, card.getUid());
            stmt.setBoolean(2, card.isActive());
            stmt.setInt(3, card.getUserId());
            stmt.setDate(4, new java.sql.Date(card.getIssueDate().getTime()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                card.setId(rs.getInt(1));
            }
        }
    }

    public Card findByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM card WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Card(
                    rs.getInt("id"),
                    rs.getString("uid"),
                    rs.getBoolean("active"),
                    rs.getInt("user_id"),
                    rs.getDate("issue_date")
                );
            }
        }
        return null;
    }

    public void deactivateCard(int userId) throws SQLException {
        String sql = "UPDATE card SET active = false WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
}
