// File: com/lostresv/dao/CardDAO.java
package com.lostresv.dao;

import com.lostresv.model.Card;
import com.lostresv.util.DatabaseConnection;

import java.sql.*;
import java.util.Date;

public class CardDAO {

    public Card getCardByUserId(int userId) throws SQLException {
        String query = "SELECT * FROM card WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String uid = rs.getString("uid");
                    boolean active = rs.getBoolean("active");
                    Date issueDate = rs.getDate("issue_date");
                    return new Card(id, uid, active, userId, issueDate);
                }
            }
        }
        return null;
    }
}
