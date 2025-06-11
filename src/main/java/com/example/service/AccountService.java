package com.example.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountService {
    private Set<String> usernames = new HashSet<>();
    private Set<String> emails = new HashSet<>();
    private static final Logger logger = Logger.getLogger(AccountService.class.getName());
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public String createAccount(String username, String password, String email) {
        if (!isValidEmail(email)) {
            return "Invalid email format.";
        }
        if (usernames.contains(username)) {
            return "Username already exists.";
        }
        if (emails.contains(email)) {
            return "Email already exists.";
        }
        String hashedPassword = hashPassword(password);
        if (hashedPassword == null) {
            return "Error hashing password.";
        }
        usernames.add(username);
        emails.add(email);
        try {
            saveAccountToDatabase(username, hashedPassword, email);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating account", e);
            return "Error creating account.";
        }
        return "Account created successfully.";
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private void saveAccountToDatabase(String username, String hashedPassword, String email) {
        // Database storage logic goes here
    }
}