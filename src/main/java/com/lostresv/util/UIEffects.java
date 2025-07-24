package com.lostresv.util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class UIEffects {

    public static void addHover(JButton button, Color normalColor, Color hoverColor) {
        button.setBackground(normalColor);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(normalColor);
            }
        });
    }

    public static void addPressedEffect(JButton button, Color colorPressed) {
        button.addMouseListener(new MouseAdapter() {
            private Color originalColor;

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    originalColor = button.getBackground();
                    button.setBackground(colorPressed);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (originalColor != null) {
                    button.setBackground(originalColor);
                }
            }
        });
    }

    public static void applyBorderFocus(JComponent component, Color normalBorder, Color focusBorder) {
        component.setBorder(new LineBorder(normalBorder, 1, true));
        component.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                component.setBorder(new LineBorder(focusBorder, 1, true));
            }

            @Override
            public void focusLost(FocusEvent e) {
                component.setBorder(new LineBorder(normalBorder, 1, true));
            }
        });
    }

    // ✅ NEW: Unified styling for dashboard/login buttons
    public static void styleButton(JButton button) {
        Color colorNormal = new Color(0x00AFC1);
        Color colorHover = new Color(0x00BFD1);
        Color colorPressed = new Color(0x008391);

        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(colorNormal);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addHover(button, colorNormal, colorHover);
        addPressedEffect(button, colorPressed);
    }
}
