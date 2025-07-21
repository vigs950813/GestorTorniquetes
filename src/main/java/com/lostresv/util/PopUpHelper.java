package com.lostresv.util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Utilidad centralizada para mostrar diálogos estilizados y coherentes en toda la app.
 * Métodos básicos usan la paleta por defecto; sobrecargas permiten colores específicos por llamada.
 */
public class PopUpHelper {

    private static final Color PRIMARY_COLOR = new Color(0x00AFC1);
    private static final Font  FONT         = new Font("Segoe UI", Font.PLAIN, 14);

    /* -------------------- API PÚBLICA (PALETA POR DEFECTO) -------------------- */

    public static void warning(Component parent, String message, String title) {
        showDialog(parent, message, title, JOptionPane.WARNING_MESSAGE, PRIMARY_COLOR, new Color(0x008391));
    }

    public static void information(Component parent, String message, String title) {
        showDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE, PRIMARY_COLOR, new Color(0x008391));
    }

    public static void error(Component parent, String message, String title) {
        showDialog(parent, message, title, JOptionPane.ERROR_MESSAGE, PRIMARY_COLOR, new Color(0x008391));
    }

    public static void success(Component parent, String message, String title) {
        showDialog(parent, message, title, JOptionPane.PLAIN_MESSAGE, PRIMARY_COLOR, new Color(0x008391));
    }

    public static boolean confirmation(Component parent, String message, String title) {
        return confirmation(parent, message, title, PRIMARY_COLOR, new Color(0x008391));
    }

    public static String inputText(Component parent, String message, String title) {
        return inputText(parent, message, title, PRIMARY_COLOR, new Color(0x008391));
    }


    /* -------------------- API PÚBLICA (COLORES PERSONALIZADOS) -------------------- */

    public static void warning(Component parent, String message, String title,
                                   Color buttonColor, Color borderColor) {
        showDialog(parent, message, title, JOptionPane.WARNING_MESSAGE, buttonColor, borderColor);
    }

    public static void information(Component parent, String message, String title,
                                   Color buttonColor, Color borderColor) {
        showDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE, buttonColor, borderColor);
    }

    public static void error(Component parent, String message, String title,
                             Color buttonColor, Color borderColor) {
        showDialog(parent, message, title, JOptionPane.ERROR_MESSAGE, buttonColor, borderColor);
    }

    public static void success(Component parent, String message, String title,
                             Color buttonColor, Color borderColor) {
        showDialog(parent, message, title, JOptionPane.PLAIN_MESSAGE, buttonColor, borderColor);
    }

    public static boolean confirmation(Component parent, String message, String title,
                                       Color buttonColor, Color borderColor) {
        applyTemporaryStyle(parent, buttonColor, borderColor);
        int resultado = JOptionPane.showConfirmDialog(parent, message, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        restoreStyle();
        return resultado == JOptionPane.YES_OPTION;
    }

    public static String inputText(Component parent, String message, String title,
                                      Color buttonColor, Color borderColor) {
        applyTemporaryStyle(parent, buttonColor, borderColor);
        String resp = JOptionPane.showInputDialog(parent, message, title, JOptionPane.PLAIN_MESSAGE);
        restoreStyle();
        return resp;
    }


    /* -------------------- IMPLEMENTACIÓN CENTRAL -------------------- */

    private static void showDialog(Component parent, String message, String title,
                                       int tipo, Color buttonColor, Color borderColor) {
        applyTemporaryStyle(parent, buttonColor, borderColor);
        JOptionPane.showMessageDialog(parent, message, title, tipo);
        restoreStyle();
    }


    /* -------------------- GESTIÓN DE ESTILOS TEMPORALES -------------------- */

    // Guardamos los valores previos para restaurarlos tras mostrar el diálogo
    private static Object oldMsgFg;
    private static Object oldPanelBg;
    private static Object oldBtnBg;
    private static Object oldBtnFg;
    private static Object oldMsgFont;
    private static Object oldBtnFont;
    private static Object oldBtnBorder;

    private static void applyTemporaryStyle(Component parent, Color buttonColor, Color borderColor) {
        // Guardar viejos valores
        oldMsgFg   = UIManager.get("OptionPane.messageForeground");
        oldPanelBg = UIManager.get("Panel.background");
        oldBtnBg   = UIManager.get("Button.background");
        oldBtnFg   = UIManager.get("Button.foreground");
        oldMsgFont = UIManager.get("OptionPane.messageFont");
        oldBtnFont = UIManager.get("Button.font");
        oldBtnBorder = UIManager.get("Button.border");

        Color backgroundColor = (parent != null) ? parent.getBackground() : Color.WHITE;
        Border borde = new LineBorder(borderColor, 1, true);

        UIManager.put("OptionPane.messageForeground", Color.DARK_GRAY);
        UIManager.put("Panel.background", backgroundColor);
        UIManager.put("Button.background", buttonColor);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("OptionPane.messageFont", FONT);
        UIManager.put("Button.font", FONT);
        UIManager.put("Button.border", borde);
        // Desactiva focus pintado si prefieres menos ruido visual:
        UIManager.put("Button.focusPainted", Boolean.FALSE);
    }

    private static void restoreStyle() {
        UIManager.put("OptionPane.messageForeground", oldMsgFg);
        UIManager.put("Panel.background",            oldPanelBg);
        UIManager.put("Button.background",            oldBtnBg);
        UIManager.put("Button.foreground",            oldBtnFg);
        UIManager.put("OptionPane.messageFont",       oldMsgFont);
        UIManager.put("Button.font",                  oldBtnFont);
        UIManager.put("Button.border",                oldBtnBorder);
    }
}
