package com.lostresv.components;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.Timer;

public class ClockLabel extends JLabel {

    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy - HH:mm:ss";
    private final SimpleDateFormat formatter;

    public ClockLabel() {
        formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        formatter.setTimeZone(TimeZone.getDefault());

        setFont(new Font("Segoe UI", Font.PLAIN, 12));
        setForeground(Color.DARK_GRAY);

        // Timer to update every second
        new Timer(1000, e -> updateClock()).start();

        updateClock();
    }

    private void updateClock() {
        setText(formatter.format(new Date()));
    }
}
