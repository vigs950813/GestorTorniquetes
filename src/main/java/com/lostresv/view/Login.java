package com.lostresv.view;

import com.lostresv.components.ClockLabel;
import com.lostresv.util.UIEffects;
import com.lostresv.components.PlaceholderPasswordField;
import com.lostresv.components.PlaceholderTextField;

import com.lostresv.model.User;
import com.lostresv.model.Employee;
import com.lostresv.model.Administrator;

import java.sql.Connection;

import com.lostresv.service.LoginService;
import com.lostresv.util.ImageLoader;
import com.lostresv.util.PopUpHelper;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Login extends JFrame {

    private PlaceholderTextField userText;
    private PlaceholderPasswordField passwordText;
    private JButton loginButton;
    private LoginService loginService;

    public Login(Connection connection) {
        this.loginService = new LoginService(connection);
        initComponents();
    }

    private void initComponents() {
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 430);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false); // recommended to avoid layout issues
        getContentPane().setBackground(new Color(240, 240, 240)); // Gris claro

        // ---------- Título ----------
        JLabel title = new JLabel("Log in", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBounds(30, 20, 340, 40);
        add(title);

        // ---------- Íconos ----------
        ImageIcon iconUser = ImageLoader.loadIcon("/icons/user.png", "👤", 24, 24);
        ImageIcon iconLock = ImageLoader.loadIcon("/icons/lock.png", "🔒", 24, 24);

        JLabel userIcon = new JLabel(iconUser);
        userIcon.setBounds(30, 90, 40, 40);
        add(userIcon);

        JLabel passwordIcon = new JLabel(iconLock);
        passwordIcon.setBounds(30, 150, 40, 40);
        add(passwordIcon);

        // ---------- Campo Usuario ----------
        userText = new PlaceholderTextField("Usuario");
        userText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userText.setBounds(75, 90, 260, 40);
        userText.setBorder(new LineBorder(Color.GRAY, 1, true));
        add(userText);

        // ---------- Campo Contraseña ----------
        passwordText = new PlaceholderPasswordField("Contraseña");
        passwordText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordText.setBounds(75, 150, 260, 40);
        passwordText.setBorder(new LineBorder(Color.GRAY, 1, true));
        add(passwordText);

        // ---------- Botón Login ----------
        loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(30, 220, 310, 45);
        loginButton.setBackground(new Color(0x00AFC1));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> handleLogin());
        add(loginButton);

        Color colorNormal = new Color(0x00AFC1);
        Color colorHover = new Color(0x00BFD1);
        Color colorPressed = new Color(0x008391);

        loginButton.setBackground(colorNormal);

        // Hover mientras pasa el mouse
        UIEffects.addHover(loginButton, colorNormal, colorHover);
        UIEffects.addPressedEffect(loginButton, colorPressed);

        UIEffects.applyBorderFocus(userText, Color.GRAY, new Color(0x00AFC1));
        UIEffects.applyBorderFocus(passwordText, Color.GRAY, new Color(0x00AFC1));

        // ---------- Clock in bottom-right corner ----------
        ClockLabel clock = new ClockLabel();
        // ---------- Clock icon ----------
        // ---------- Clock in bottom-right corner ----------
        ImageIcon clockIcon = ImageLoader.loadIcon("/icons/clock.png", "🕒", 20, 20);
        // Create panel to hold icon and clock aligned properly
        JPanel clockPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        clockPanel.setOpaque(false);
        clockPanel.setBounds(180, 360, 180, 30);  // adjust Y position for good bottom alignment

        JLabel clockIconLabel = new JLabel(clockIcon);
        clockIconLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        clock.setAlignmentY(Component.CENTER_ALIGNMENT);

        clockPanel.add(clockIconLabel);
        clockPanel.add(clock);
        add(clockPanel);

    }

    private void handleLogin() {
        String username = userText.getText().trim();
        String password = new String(passwordText.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            PopUpHelper.warning(this, "Los campos están vacíos.", "Alerta");
            return;
        }

        loginButton.setEnabled(false); // block UI button

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            private User user;

            @Override
            protected Void doInBackground() {
                try {
                    user = loginService.authenticate(username, password);
                } catch (Exception e) {
                    e.printStackTrace();
                    PopUpHelper.error(Login.this, "Login error: " + e.getMessage(), "Error");
                }
                return null;
            }

            @Override
            protected void done() {
                loginButton.setEnabled(true); // re-enable after attempt

                if (user == null) {
                    PopUpHelper.error(Login.this, "Datos incorrectos, intenta de nuevo.", "Error");
                } else if (user instanceof Administrator) {
                    PopUpHelper.information(Login.this, "Bienvenido Administrador: " + user.getName(), "Informacion");
                    dispose();
                    new Dashboard(user).setVisible(true);
                } else if (user instanceof Employee) {
                    PopUpHelper.information(Login.this, "Bienvenido: " + user.getName(), "Informacion");
                    dispose();
                    new Dashboard(user).setVisible(true);
                }
            }
        };

        worker.execute(); // run the login logic in the background
    }

}
