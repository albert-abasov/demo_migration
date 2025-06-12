package com.example.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{3,20}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public List<String> validateRegistrationInput(String username, String password, String email) {
        List<String> errorMessages = new ArrayList<>();

        if (username == null || username.length() < 3 || username.length() > 20 || !Pattern.matches(USERNAME_PATTERN, username)) {
            errorMessages.add("Invalid username. It must be 3-20 characters long and contain only letters and numbers.");
        }
        if (password == null || password.length() < 6 || password.length() > 20 || !Pattern.matches(PASSWORD_PATTERN, password)) {
            errorMessages.add("Invalid password. It must be 6-20 characters long and contain at least one letter and one number.");
        }
        if (email == null || !Pattern.matches(EMAIL_PATTERN, email)) {
            errorMessages.add("Invalid email format.");
        }

        return errorMessages;
    }
}