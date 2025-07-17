package com.lostresv.main;

import javax.swing.SwingUtilities;
import com.lostresv.view.Login;

public class GestorTorniquetes {

    public static void main(String[] args) {
        // Ejecutar en hilo de interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });
    }
}
