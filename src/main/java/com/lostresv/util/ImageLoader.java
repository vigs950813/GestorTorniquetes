package com.lostresv.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ImageLoader {

    /**
     * Carga una imagen desde el classpath. Si no se encuentra, devuelve una imagen con text.
     *
     * @param resourcePath Ruta relativa en recursos, ej: "/icons/user.png"
     * @param fallbackText Texto o emoji de respaldo
     * @return ImageIcon con tamaño original
     */
    public static ImageIcon loadIcon(String resourcePath, String fallbackText) {
        return loadIcon(resourcePath, fallbackText, 24, 24);
    }

    /**
     * Carga una imagen con tamaño personalizado. Si no se encuentra, genera imagen con text de respaldo.
     *
     * @param resourcePath Ruta del recurso
     * @param fallbackText Texto o emoji para fallback
     * @param width Ancho deseado
     * @param height Alto deseado
     * @return ImageIcon con tamaño personalizado
     */
    public static ImageIcon loadIcon(String resourcePath, String fallbackText, int width, int height) {
        URL url = ImageLoader.class.getResource(resourcePath);

        if (url != null) {
            ImageIcon original = new ImageIcon(url);
            Image img = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } else {
            System.err.println("⚠️ Recurso no encontrado: " + resourcePath + " — usando fallback.");
            return generateIconFromText(fallbackText, width, height);
        }
    }

    /**
     * Genera una imagen con text centrado, útil como fallback visual.
     *
     * @param text Texto o emoji
     * @param width Ancho
     * @param height Alto
     * @return ImageIcon generado
     */
    private static ImageIcon generateIconFromText(String text, int width, int height) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, Math.min(width, height) - 6));
        label.setSize(width, height);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        label.paint(g);
        g.dispose();

        return new ImageIcon(image);
    }
}
