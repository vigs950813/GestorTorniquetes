package com.lostresv.main;

import javax.swing.SwingUtilities;
import com.lostresv.view.Login;
import com.lostresv.util.DatabaseConnection;
import java.sql.Connection;

public class GestorTorniquetes {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Connection connection = DatabaseConnection.getConnection();
                Login login = new Login(connection);
                login.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
