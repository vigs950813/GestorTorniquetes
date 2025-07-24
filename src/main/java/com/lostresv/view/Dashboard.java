package com.lostresv.view;

import com.lostresv.components.ClockLabel;
import com.lostresv.model.User;
import com.lostresv.util.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    
    private User user;
    
    public Dashboard(User user) {
        this.user = user;
        
        
        setTitle("Panel de control - Administrador " + user.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel that holds all 3 columns
        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add three columns with custom icons
        mainPanel.add(createColumnPanel("Users", "/icons/user.png", new String[]{"Agregar Usuario", "Editar Usuario", "Borrar Usuario"}));
        mainPanel.add(createColumnPanel("Access Points", "/icons/access.png", new String[]{"Agregar Torniquete", "Editar Torniquete", "Eliminar Torniquete"}));
        mainPanel.add(createThirdColumnPanel());

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createColumnPanel(String title, String imagePath, String[] buttonLabels) {
        JPanel columnPanel = new JPanel();
        columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.Y_AXIS));
        columnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        columnPanel.add(titleLabel);

        columnPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Image with fallback
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon icon = ImageLoader.loadIcon(imagePath, "❓", 64, 64);
        imageLabel.setIcon(icon);
        columnPanel.add(imageLabel);

        columnPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Buttons
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(180, 30));
            columnPanel.add(button);
            columnPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        return columnPanel;
    }

    private JPanel createThirdColumnPanel() {
        JPanel thirdColumn = new JPanel(new BorderLayout());
        thirdColumn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top: Title + image + buttons
        JPanel topPanel = createColumnPanel("System", "/icons/system.png", new String[]{"Estatus", "Reportes", "Cerrar Sesión"});
        thirdColumn.add(topPanel, BorderLayout.CENTER);

        // Bottom-right clock
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(new ClockLabel());
        thirdColumn.add(bottomPanel, BorderLayout.SOUTH);

        return thirdColumn;
    }

}
