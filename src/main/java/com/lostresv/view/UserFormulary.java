package com.lostresv.view;

import com.lostresv.util.UIEffects;

import javax.swing.*;
import java.awt.*;

public class UserFormulary extends JPanel {
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeCombo;
    private JTextField cardUidField;
    private JButton submitButton;
    private JButton returnButton;

    public UserFormulary(Runnable onReturn) {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 400)); // Enlarged panel

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);

        JLabel userTypeLabel = new JLabel("User Type:");
        String[] userTypes = {"Administrator", "Employee", "Visitor"};
        userTypeCombo = new JComboBox<>(userTypes);

        JLabel cardUidLabel = new JLabel("Card UID:");
        cardUidField = new JTextField(15);

        submitButton = new JButton("Create User");
        returnButton = new JButton("← Return");

        // Style buttons
        UIEffects.addHover(submitButton, new Color(70, 130, 180), new Color(100, 149, 237));
        UIEffects.addPressedEffect(submitButton, new Color(65, 105, 225));

        UIEffects.addHover(returnButton, new Color(200, 200, 200), new Color(220, 220, 220));
        UIEffects.addPressedEffect(returnButton, new Color(180, 180, 180));

        // Layout setup
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = 0;
        add(nameLabel, gbc);      gbc.gridx = 1; add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(usernameLabel, gbc);  gbc.gridx = 1; add(usernameField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(passwordLabel, gbc);  gbc.gridx = 1; add(passwordField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(userTypeLabel, gbc);  gbc.gridx = 1; add(userTypeCombo, gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(cardUidLabel, gbc);   gbc.gridx = 1; add(cardUidField, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        add(submitButton, gbc);

        gbc.gridy++;
        add(returnButton, gbc); // ⬅️ Add the return button to UI

        userTypeCombo.addActionListener(e -> toggleCardField());

        // Ensure correct initial visibility for card UID field based on selected user type
        toggleCardField();

        submitButton.addActionListener(e -> handleCreateUser());
        returnButton.addActionListener(e -> onReturn.run());
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

        System.out.println("Creating user:");
        System.out.printf("Name: %s, Username: %s, Password: %s, Type: %s, Card UID: %s%n",
                name, username, password, userType, cardUid);
    }
}
