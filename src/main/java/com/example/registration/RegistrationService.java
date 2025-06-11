package com.example.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$");

    public void registerUser(String username, String password) throws RegistrationException {
        validateInputs(username, password);
        try {
            String hashedPassword = passwordEncoder.encode(password);
            User user = new User(username, hashedPassword, "ACTIVE", "USER");
            userRepository.save(user);
        } catch (Exception e) {
            throw new RegistrationException("Account creation failed", e);
        }
    }

    private void validateInputs(String username, String password) {
        if (Objects.isNull(username) || !USERNAME_PATTERN.matcher(username).matches()) {
            throw new IllegalArgumentException("Invalid username");
        }
        if (Objects.isNull(password) || !PASSWORD_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("Invalid password");
        }
    }
}