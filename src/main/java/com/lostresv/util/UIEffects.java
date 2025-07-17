package com.lostresv.util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class UIEffects {

    /**
     * Aplica efecto hover y clic a un botón.
     *
     * @param boton JButton al que se aplican los eventos.
     * @param colorNormal Color por defecto.
     * @param colorHover Color al pasar el mouse.
     * @param colorPressed Color al presionar clic.
     */
    public static void agregarHover(JButton boton, Color colorNormal, Color colorHover) {
        boton.setBackground(colorNormal);

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(colorHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(colorNormal);
            }
        });
    }

    public static void agregarPressedEffect(JButton boton, Color colorPressed) {
        boton.addMouseListener(new MouseAdapter() {
            private Color originalColor;

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    originalColor = boton.getBackground();
                    boton.setBackground(colorPressed);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (originalColor != null) {
                    boton.setBackground(originalColor);
                }
            }
        });
    }

    public static void aplicarBordeFocus(JComponent componente, Color bordeNormal, Color bordeFocus) {
        componente.setBorder(new LineBorder(bordeNormal, 1, true));
        componente.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                componente.setBorder(new LineBorder(bordeFocus, 1, true));
            }

            @Override
            public void focusLost(FocusEvent e) {
                componente.setBorder(new LineBorder(bordeNormal, 1, true));
            }
        });
    }
}
