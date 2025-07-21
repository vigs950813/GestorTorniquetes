package com.lostresv.view;

import com.lostresv.components.ClockLabel;
import com.lostresv.util.UIEffects;
import com.lostresv.components.PlaceholderPasswordField;
import com.lostresv.components.PlaceholderTextField;
import com.lostresv.util.ImageLoader;
import com.lostresv.util.PopUpHelper;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Login extends JFrame {

    private PlaceholderTextField userText;
    private PlaceholderPasswordField passwordText;
    private JButton loginButton;

    public Login() {
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
        passwordText = new PlaceholderPasswordField("Password");
        passwordText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordText.setBounds(75, 150, 260, 40);
        passwordText.setBorder(new LineBorder(Color.GRAY, 1, true));
        add(passwordText);

        // ---------- Botón Login ----------
        loginButton = new JButton("Log in");
        loginButton.setBounds(30, 220, 310, 45);
        loginButton.setBackground(new Color(0x00AFC1));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> validarDatos());
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
        ImageIcon clockIcon = ImageLoader.loadIcon("/icons/clock.png", "🕒", 20, 20);
        JLabel clockLabel = new JLabel(clockIcon);
        clockLabel.setBounds(getWidth() - 185, getHeight() - 83, 20, 20); // Adjust for spacing
        add(clockLabel);

        clock.setBounds(getWidth() - 160, getHeight() - 80, 140, 20); // Adjust margin
        add(clock);


    }

    private void validarDatos() {
        String usuario = userText.getRealText();
        String clave = passwordText.getRealText();

        if (usuario.isEmpty() || clave.isEmpty()) {
            PopUpHelper.warning(this, "Por favor ingresa usuario y contraseña válidos.", "Alerta");
        } else {
            PopUpHelper.success(this, "Inicio de sesión exitoso (simulado)", "Bienvenido");
            // Aquí puedes abrir la siguiente ventana según el rol
        }
    }
}
