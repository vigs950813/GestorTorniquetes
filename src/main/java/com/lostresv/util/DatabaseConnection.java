// File: com/lostresv/util/DatabaseConnection.java
package com.lostresv.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/torniquetes_db"; // change DB name if needed
    private static final String USER = "root";
    private static final String PASSWORD = "6CAF8D7D1292BBC04A7716@#$";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
