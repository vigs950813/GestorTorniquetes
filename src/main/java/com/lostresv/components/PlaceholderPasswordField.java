package com.lostresv.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceholderPasswordField extends JPasswordField {

    private final String placeholder;

    public PlaceholderPasswordField(String placeholder) {
        this.placeholder = placeholder;
        setText(placeholder);
        setEchoChar((char) 0);
        setForeground(Color.LIGHT_GRAY);
        setHorizontalAlignment(SwingConstants.CENTER);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(getPassword()).equals(placeholder)) {
                    setText("");
                    setEchoChar('•');
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(getPassword()).isEmpty()) {
                    setText(placeholder);
                    setEchoChar((char) 0);
                    setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    public boolean isEmptyOrPlaceholder() {
        String value = String.valueOf(getPassword()).trim();
        return value.isEmpty() || value.equals(placeholder);
    }

    public String getRealText() {
        return isEmptyOrPlaceholder() ? "" : String.valueOf(getPassword()).trim();
    }
}
