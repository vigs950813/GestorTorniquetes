package com.lostresv.view;

import com.lostresv.components.ClockLabel;
import com.lostresv.model.User;
import com.lostresv.util.ImageLoader;
import com.lostresv.util.UIEffects;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    private final User user;
    private JPanel contentPanel;

    public Dashboard(User user) {
        this.user = user;

        setTitle("Dashboard – " + user.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(240, 240, 240)); // Background

        showMainDashboard(); // Load initial view
    }

    private void showMainDashboard() {
        if (contentPanel != null) {
            getContentPane().remove(contentPanel);
        }

        contentPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setOpaque(false);

        // Column: Usuarios
        contentPanel.add(createColumnPanel(
            "Usuarios",
            ImageLoader.loadIcon("/images/user.png", "👤", 100, 100),
            new String[]{"Agregar Usuario", "Editar Usuario", "Borrar Usuario"}
        ));

        // Column: Torniquetes
        contentPanel.add(createColumnPanel(
            "Torniquetes",
            ImageLoader.loadIcon("/images/turnstile.png", "⫢", 100, 100),
            new String[]{"Agregar Torniquete", "Editar Torniquete", "Eliminar Torniquete"}
        ));

        // Column: Sistema
        contentPanel.add(createThirdColumnPanel());

        getContentPane().add(contentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private JPanel createColumnPanel(String title, ImageIcon icon, String[] buttonLabels) {
        JPanel columnPanel = new JPanel();
        columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.Y_AXIS));
        columnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        columnPanel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        columnPanel.add(titleLabel);
        columnPanel.add(Box.createVerticalStrut(10));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        columnPanel.add(iconLabel);
        columnPanel.add(Box.createVerticalStrut(15));

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            UIEffects.styleButton(button);

            if (label.equals("Agregar Usuario")) {
                button.addActionListener(e -> showUserFormulary());
            }

            columnPanel.add(button);
            columnPanel.add(Box.createVerticalStrut(10));
        }

        return columnPanel;
    }

    private JPanel createThirdColumnPanel() {
        JPanel thirdColumnPanel = new JPanel();
        thirdColumnPanel.setLayout(new BoxLayout(thirdColumnPanel, BoxLayout.Y_AXIS));
        thirdColumnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        thirdColumnPanel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Sistema", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        thirdColumnPanel.add(titleLabel);
        thirdColumnPanel.add(Box.createVerticalStrut(10));

        ImageIcon icon = ImageLoader.loadIcon("/images/configuracion.png", "⚙", 100, 100);
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        thirdColumnPanel.add(iconLabel);
        thirdColumnPanel.add(Box.createVerticalStrut(15));

        String[] systemButtons = {"Estado", "Reportes", "Configuración", "Cerrar Sesión"};
        for (String label : systemButtons) {
            JButton button = new JButton(label);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            UIEffects.styleButton(button);
            thirdColumnPanel.add(button);
            thirdColumnPanel.add(Box.createVerticalStrut(10));
        }

        // Spacer to push clock to bottom
        thirdColumnPanel.add(Box.createVerticalGlue());

        // Clock with icon
        JPanel clockPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        clockPanel.setOpaque(false);
        clockPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel clockLabel = new JLabel(ImageLoader.loadIcon("/icons/clock.png", "🕒", 20, 20));
        ClockLabel clock = new ClockLabel();

        clockLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        clock.setAlignmentY(Component.CENTER_ALIGNMENT);

        clockPanel.add(clockLabel);
        clockPanel.add(clock);

        thirdColumnPanel.add(clockPanel);

        return thirdColumnPanel;
    }

    private void showUserFormulary() {
        getContentPane().remove(contentPanel);
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setOpaque(false);

        UserFormulary userForm = new UserFormulary(() -> showMainDashboard());
        contentPanel.add(userForm, BorderLayout.CENTER);

        getContentPane().add(contentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
