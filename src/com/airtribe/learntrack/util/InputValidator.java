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

        if (email.contains(" ")) {
            throw new InvalidInputException("Email cannot contain spaces.");
        }

        int at = email.indexOf('@');
        if (at == -1 || at != email.lastIndexOf('@')) {
            throw new InvalidInputException("Email must contain exactly one '@'.");
        }

        String local = email.substring(0, at);
        String domain = email.substring(at + 1);

        if (local.isEmpty()) {
            throw new InvalidInputException("Email must have a name before '@'.");
        }
        if (domain.isEmpty()) {
            throw new InvalidInputException("Email must have a domain after '@'.");
        }

        int dot = domain.lastIndexOf('.');
        if (dot == -1) {
            throw new InvalidInputException("Email domain must contain a '.' (e.g. gmail.com).");
        }
        if (domain.startsWith(".") || domain.endsWith(".") || domain.contains("..")) {
            throw new InvalidInputException("Email domain is not valid.");
        }
        if (domain.length() - dot - 1 < 2) {
            throw new InvalidInputException("Email domain ending is too short (e.g. .com).");
        }

        return email;
    }
}