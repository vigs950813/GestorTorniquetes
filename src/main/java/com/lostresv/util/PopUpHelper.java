package com.lostresv.util;

import javax.swing.*;
import java.awt.*;

public class PopUpHelper {

    private static final Color COLOR_PRIMARIO = new Color(0x00AFC1);
    private static final Font FUENTE = new Font("Segoe UI", Font.PLAIN, 14);

    public static void advertencia(Component parent, String mensaje, String titulo) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
    }

    public static void informacion(Component parent, String mensaje, String titulo) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error(Component parent, String mensaje, String titulo) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static void exito(Component parent, String mensaje, String titulo) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Muestra un cuadro de confirmación (Sí / No) y devuelve la respuesta como boolean.
     */
    public static boolean confirmacion(Component parent, String mensaje, String titulo) {
        aplicarEstilos();
        int resultado = JOptionPane.showConfirmDialog(parent, mensaje, titulo,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return resultado == JOptionPane.YES_OPTION;
    }

    /**
     * Solicita un valor de entrada textual al usuario y lo devuelve.
     */
    public static String entradaTexto(Component parent, String mensaje, String titulo) {
        aplicarEstilos();
        return JOptionPane.showInputDialog(parent, mensaje, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    // Métodos base privados
    private static void mostrarDialogo(Component parent, String mensaje, String titulo, int tipo) {
        aplicarEstilos();
        JOptionPane.showMessageDialog(parent, mensaje, titulo, tipo);
    }

    private static void aplicarEstilos() {
        UIManager.put("OptionPane.messageForeground", Color.DARK_GRAY);
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("Button.background", COLOR_PRIMARIO);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("OptionPane.messageFont", FUENTE);
        UIManager.put("Button.font", FUENTE);
    }
}
