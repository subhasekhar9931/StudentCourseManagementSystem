package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

public class InputValidator {
    private InputValidator() {}

    public static int parseInt(String text, String fieldName) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a whole number.");
        }
    }

    public static String requireNonEmpty(String text, String fieldName) {
        if (text == null || text.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
        return text.trim();
    }

    public static String requireEmail(String text) {
        String email = requireNonEmpty(text, "Email");
        if (!email.contains("@") || !email.contains(".")) {
            throw new InvalidInputException("Email is not valid.");
        }
        return email;
    }
}