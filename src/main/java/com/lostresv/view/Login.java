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

    private PlaceholderTextField textUsuario;
    private PlaceholderPasswordField textPassword;
    private JButton botonIniciar;

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
        JLabel titulo = new JLabel("Log in", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setBounds(30, 20, 340, 40);
        add(titulo);

        // ---------- Íconos ----------
        ImageIcon iconUser = ImageLoader.cargarIcono("/icons/user.png", "👤", 24, 24);
        ImageIcon iconLock = ImageLoader.cargarIcono("/icons/lock.png", "🔒", 24, 24);

        JLabel iconUsuario = new JLabel(iconUser);
        iconUsuario.setBounds(30, 90, 40, 40);
        add(iconUsuario);

        JLabel iconContrasena = new JLabel(iconLock);
        iconContrasena.setBounds(30, 150, 40, 40);
        add(iconContrasena);

        // ---------- Campo Usuario ----------
        textUsuario = new PlaceholderTextField("Usuario");
        textUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textUsuario.setBounds(75, 90, 260, 40);
        textUsuario.setBorder(new LineBorder(Color.GRAY, 1, true));
        add(textUsuario);

        // ---------- Campo Contraseña ----------
        textPassword = new PlaceholderPasswordField("Password");
        textPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textPassword.setBounds(75, 150, 260, 40);
        textPassword.setBorder(new LineBorder(Color.GRAY, 1, true));
        add(textPassword);

        // ---------- Botón Login ----------
        botonIniciar = new JButton("Log in");
        botonIniciar.setBounds(30, 220, 310, 45);
        botonIniciar.setBackground(new Color(0x00AFC1));
        botonIniciar.setForeground(Color.WHITE);
        botonIniciar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botonIniciar.setFocusPainted(false);
        botonIniciar.setBorderPainted(false);
        botonIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonIniciar.addActionListener(e -> validarDatos());
        add(botonIniciar);

        Color colorNormal = new Color(0x00AFC1);
        Color colorHover = new Color(0x00BFD1);
        Color colorPressed = new Color(0x008391);

        botonIniciar.setBackground(colorNormal);

        // Hover mientras pasa el mouse
        UIEffects.addHover(botonIniciar, colorNormal, colorHover);
        UIEffects.addPressedEffect(botonIniciar, colorPressed);

        UIEffects.applyBorderFocus(textUsuario, Color.GRAY, new Color(0x00AFC1));
        UIEffects.applyBorderFocus(textPassword, Color.GRAY, new Color(0x00AFC1));
        
                // ---------- Clock in bottom-right corner ----------
        ClockLabel clock = new ClockLabel();
                // ---------- Clock icon ----------
        ImageIcon clockIcon = ImageLoader.cargarIcono("/icons/clock.png", "🕒", 20, 20);
        JLabel clockLabel = new JLabel(clockIcon);
        clockLabel.setBounds(getWidth() - 185, getHeight() - 83, 20, 20); // Adjust for spacing
        add(clockLabel);

        clock.setBounds(getWidth() - 160, getHeight() - 80, 140, 20); // Adjust margin
        add(clock);


    }

    private void validarDatos() {
        String usuario = textUsuario.getRealText();
        String clave = textPassword.getRealText();

        if (usuario.isEmpty() || clave.isEmpty()) {
            PopUpHelper.warning(this, "Por favor ingresa usuario y contraseña válidos.", "Alerta");
        } else {
            PopUpHelper.success(this, "Inicio de sesión exitoso (simulado)", "Bienvenido");
            // Aquí puedes abrir la siguiente ventana según el rol
        }
    }
}
