package com.example.registration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegistrationValidator {
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{3,20}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public List<String> validateUsername(String username) {
        List<String> errors = new ArrayList<>();
        if (username == null || !USERNAME_PATTERN.matcher(username).matches()) {
            errors.add("Invalid username. It must be 3-20 characters long and can only contain letters and numbers.");
        }
        return errors;
    }

    public List<String> validatePassword(String password) {
        List<String> errors = new ArrayList<>();
        if (password == null || !PASSWORD_PATTERN.matcher(password).matches()) {
            errors.add("Invalid password. It must be 6-20 characters long and contain at least one letter and one number.");
        }
        return errors;
    }

    public List<String> validateEmail(String email) {
        List<String> errors = new ArrayList<>();
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            errors.add("Invalid email format.");
        }
        return errors;
    }

    public List<String> validateAll(String username, String password, String email) {
        List<String> errors = new ArrayList<>();
        errors.addAll(validateUsername(username));
        errors.addAll(validatePassword(password));
        errors.addAll(validateEmail(email));
        return errors;
    }
}