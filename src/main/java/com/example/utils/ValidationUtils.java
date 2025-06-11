package com.example.utils;

import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    public static boolean isValidUsername(String username) {
        return username != null && !username.isEmpty() && username.length() >= 3 && username.length() <= 20;
    }

    public static boolean isValidPassword(String password) {
        return password != null && !password.isEmpty() && password.length() >= 8 && password.length() <= 30;
    }

    public static boolean isValidEmail(String email) {
        return email != null && !email.isEmpty() && email.length() >= 5 && email.length() <= 50 && EMAIL_PATTERN.matcher(email).matches();
    }
}