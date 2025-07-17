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

    private static final Color COLOR_PRIMARIO = new Color(0x00AFC1);
    private static final Font  FUENTE         = new Font("Segoe UI", Font.PLAIN, 14);

    /* -------------------- API PÚBLICA (PALETA POR DEFECTO) -------------------- */

    public static void advertencia(Component parent, String mensaje, String titulo) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.WARNING_MESSAGE, COLOR_PRIMARIO, new Color(0x008391));
    }

    public static void informacion(Component parent, String mensaje, String titulo) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE, COLOR_PRIMARIO, new Color(0x008391));
    }

    public static void error(Component parent, String mensaje, String titulo) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.ERROR_MESSAGE, COLOR_PRIMARIO, new Color(0x008391));
    }

    public static void exito(Component parent, String mensaje, String titulo) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.PLAIN_MESSAGE, COLOR_PRIMARIO, new Color(0x008391));
    }

    public static boolean confirmacion(Component parent, String mensaje, String titulo) {
        return confirmacion(parent, mensaje, titulo, COLOR_PRIMARIO, new Color(0x008391));
    }

    public static String entradaTexto(Component parent, String mensaje, String titulo) {
        return entradaTexto(parent, mensaje, titulo, COLOR_PRIMARIO, new Color(0x008391));
    }


    /* -------------------- API PÚBLICA (COLORES PERSONALIZADOS) -------------------- */

    public static void advertencia(Component parent, String mensaje, String titulo,
                                   Color colorBoton, Color colorBorde) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.WARNING_MESSAGE, colorBoton, colorBorde);
    }

    public static void informacion(Component parent, String mensaje, String titulo,
                                   Color colorBoton, Color colorBorde) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE, colorBoton, colorBorde);
    }

    public static void error(Component parent, String mensaje, String titulo,
                             Color colorBoton, Color colorBorde) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.ERROR_MESSAGE, colorBoton, colorBorde);
    }

    public static void exito(Component parent, String mensaje, String titulo,
                             Color colorBoton, Color colorBorde) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.PLAIN_MESSAGE, colorBoton, colorBorde);
    }

    public static boolean confirmacion(Component parent, String mensaje, String titulo,
                                       Color colorBoton, Color colorBorde) {
        aplicarEstilosTemporal(parent, colorBoton, colorBorde);
        int resultado = JOptionPane.showConfirmDialog(parent, mensaje, titulo,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        restaurarEstilos();
        return resultado == JOptionPane.YES_OPTION;
    }

    public static String entradaTexto(Component parent, String mensaje, String titulo,
                                      Color colorBoton, Color colorBorde) {
        aplicarEstilosTemporal(parent, colorBoton, colorBorde);
        String resp = JOptionPane.showInputDialog(parent, mensaje, titulo, JOptionPane.PLAIN_MESSAGE);
        restaurarEstilos();
        return resp;
    }


    /* -------------------- IMPLEMENTACIÓN CENTRAL -------------------- */

    private static void mostrarDialogo(Component parent, String mensaje, String titulo,
                                       int tipo, Color colorBoton, Color colorBorde) {
        aplicarEstilosTemporal(parent, colorBoton, colorBorde);
        JOptionPane.showMessageDialog(parent, mensaje, titulo, tipo);
        restaurarEstilos();
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

    private static void aplicarEstilosTemporal(Component parent, Color colorBoton, Color colorBorde) {
        // Guardar viejos valores
        oldMsgFg   = UIManager.get("OptionPane.messageForeground");
        oldPanelBg = UIManager.get("Panel.background");
        oldBtnBg   = UIManager.get("Button.background");
        oldBtnFg   = UIManager.get("Button.foreground");
        oldMsgFont = UIManager.get("OptionPane.messageFont");
        oldBtnFont = UIManager.get("Button.font");
        oldBtnBorder = UIManager.get("Button.border");

        Color backgroundColor = (parent != null) ? parent.getBackground() : Color.WHITE;
        Border borde = new LineBorder(colorBorde, 1, true);

        UIManager.put("OptionPane.messageForeground", Color.DARK_GRAY);
        UIManager.put("Panel.background", backgroundColor);
        UIManager.put("Button.background", colorBoton);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("OptionPane.messageFont", FUENTE);
        UIManager.put("Button.font", FUENTE);
        UIManager.put("Button.border", borde);
        // Desactiva focus pintado si prefieres menos ruido visual:
        UIManager.put("Button.focusPainted", Boolean.FALSE);
    }

    private static void restaurarEstilos() {
        UIManager.put("OptionPane.messageForeground", oldMsgFg);
        UIManager.put("Panel.background",            oldPanelBg);
        UIManager.put("Button.background",            oldBtnBg);
        UIManager.put("Button.foreground",            oldBtnFg);
        UIManager.put("OptionPane.messageFont",       oldMsgFont);
        UIManager.put("Button.font",                  oldBtnFont);
        UIManager.put("Button.border",                oldBtnBorder);
    }
}
