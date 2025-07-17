package com.lostresv.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceholderTextField extends JTextField {

    private final String placeholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        setText(placeholder);
        setForeground(Color.LIGHT_GRAY);
        setHorizontalAlignment(SwingConstants.CENTER);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    public boolean isEmptyOrPlaceholder() {
        return getText().trim().isEmpty() || getText().equals(placeholder);
    }

    public String getRealText() {
        return isEmptyOrPlaceholder() ? "" : getText().trim();
    }
}
