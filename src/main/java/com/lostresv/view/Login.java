package com.lostresv.view;

import com.lostresv.util.ImageLoader;
import com.lostresv.util.PopUpHelper;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame {

    private JTextField textUsuario;
    private JPasswordField textPassword;
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

        // ---------- Título ----------
        JLabel titulo = new JLabel("Log in", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setBounds(30, 20, 340, 40);
        add(titulo);

        // ---------- Íconos con fallback ----------
        ImageIcon iconUser = ImageLoader.cargarIcono("/icons/user.png", "👤", 24, 24);
        ImageIcon iconLock = ImageLoader.cargarIcono("/icons/lock.png", "🔒", 24, 24);

        JLabel iconUsuario = new JLabel(iconUser);
        iconUsuario.setBounds(30, 90, 40, 40);
        add(iconUsuario);

        JLabel iconContrasena = new JLabel(iconLock);
        iconContrasena.setBounds(30, 150, 40, 40);
        add(iconContrasena);

        // ---------- Campo Usuario ----------
        textUsuario = new JTextField("johndoe@gmail.com");
        textUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textUsuario.setBounds(75, 90, 260, 40);
        textUsuario.setHorizontalAlignment(SwingConstants.LEFT);
        textUsuario.setBorder(new LineBorder(Color.GRAY, 1, true));
        add(textUsuario);

        // Placeholder usuario
        textUsuario.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textUsuario.getText().equals("johndoe@gmail.com")) {
                    textUsuario.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (textUsuario.getText().isEmpty()) {
                    textUsuario.setText("johndoe@gmail.com");
                }
            }
        });

        // ---------- Campo Contraseña ----------
        textPassword = new JPasswordField("Password");
        textPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textPassword.setBounds(75, 150, 260, 40);
        textPassword.setHorizontalAlignment(SwingConstants.LEFT);
        textPassword.setBorder(new LineBorder(Color.GRAY, 1, true));
        textPassword.setEchoChar((char) 0); // visible mientras sea placeholder
        add(textPassword);

        // Placeholder contraseña
        textPassword.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(textPassword.getPassword()).equals("Password")) {
                    textPassword.setText("");
                    textPassword.setEchoChar('•');
                }
            }

            public void focusLost(FocusEvent e) {
                if (String.valueOf(textPassword.getPassword()).isEmpty()) {
                    textPassword.setText("Password");
                    textPassword.setEchoChar((char) 0);
                }
            }
        });

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
    }

    private void validarDatos() {
        String usuario = textUsuario.getText().trim();
        String clave = new String(textPassword.getPassword()).trim();

        if (usuario.isEmpty() || clave.isEmpty()
                || usuario.equals("johndoe@gmail.com") || clave.equals("Password")) {
            PopUpHelper.advertencia(this, "Por favor ingresa usuario y contraseña válidos.", "Validación");
        } else {
            PopUpHelper.exito(this, "Inicio de sesión exitoso (simulado)", "Bienvenido");
            // Aquí podrías llamar al siguiente panel o ventana
        }
    }
}
