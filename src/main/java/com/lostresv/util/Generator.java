package com.lostresv.util;

import java.security.SecureRandom;
import java.util.Locale;

public class Generator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase(Locale.ROOT);
    private static final String DIGITS = "0123456789";
    private static final String ALPHANUM = UPPER + LOWER + DIGITS;
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Generates a username with total length between 6 and 10 characters.
     */
    public static String generateUsername(String baseName) {
        String clean = baseName.trim().replaceAll("\\s+", "").toLowerCase();

        // Restrict base to max 7 characters to allow suffix space
        int maxBaseLength = 7;
        if (clean.length() > maxBaseLength) {
            clean = clean.substring(0, maxBaseLength);
        }

        // Add numeric suffix to fill up to 6–10 characters
        int minLength = 6;
        int maxLength = 10;

        int remaining = maxLength - clean.length();
        int suffixLength = remaining > 0 ? RANDOM.nextInt(remaining + 1) : 0;

        StringBuilder suffix = new StringBuilder();
        for (int i = 0; i < suffixLength; i++) {
            suffix.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        }

        String username = clean + suffix.toString();

        // Make sure it's at least 6 characters
        while (username.length() < minLength) {
            username += DIGITS.charAt(RANDOM.nextInt(DIGITS.length()));
        }

        return username;
    }

    /**
     * Generates a password with random length between 6 and 10.
     */
    public static String generatePassword() {
        int length = 6 + RANDOM.nextInt(5); // 6 to 10
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUM.charAt(RANDOM.nextInt(ALPHANUM.length())));
        }
        return sb.toString();
    }
    
}
