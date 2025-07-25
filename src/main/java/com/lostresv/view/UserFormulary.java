package com.lostresv.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserFormulary extends JPanel {
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeCombo;
    private JTextField cardUidField;
    private JButton submitButton;

    public UserFormulary() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel nameLabel = new JLabel("Nombre:");
        nameField = new JTextField(15);

        JLabel usernameLabel = new JLabel("Usuario:");
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField(15);

        JLabel userTypeLabel = new JLabel("Tipo de usuario:");
        String[] userTypes = {"Administrador", "empleado", "Visitante"};
        userTypeCombo = new JComboBox<>(userTypes);

        JLabel cardUidLabel = new JLabel("Card UID:");
        cardUidField = new JTextField(15);

        submitButton = new JButton("Crear Usuario");

        // Layout
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = 0;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(userTypeLabel, gbc);
        gbc.gridx = 1;
        add(userTypeCombo, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(cardUidLabel, gbc);
        gbc.gridx = 1;
        add(cardUidField, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Behavior
        userTypeCombo.addActionListener(e -> toggleCardField());
        toggleCardField(); // Initial visibility

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCreateUser();
            }
        });
    }

    private void toggleCardField() {
        String selected = (String) userTypeCombo.getSelectedItem();
        boolean needsCard = selected.equals("Visitor");
        cardUidField.setEnabled(needsCard);
    }

    private void handleCreateUser() {
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String userType = (String) userTypeCombo.getSelectedItem();
        String cardUid = cardUidField.getText();

        // Here you should call your controller or service layer
        System.out.println("Creando usuario");
        System.out.printf("Name: %s, Username: %s, Password: %s, Type: %s, Card UID: %s%n",
                name, username, password, userType, cardUid);

        // TODO: Validate and delegate to business logic
    }
    
       public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("User Formulary");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new UserFormulary());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
