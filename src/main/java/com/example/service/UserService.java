package com.example.service;

import com.example.repository.UserRepository;
import com.example.exception.UserCreationException;
import com.example.model.UserRegistrationForm;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createAccount(UserRegistrationForm form) throws UserCreationException {
        validateForm(form);
        if (userRepository.existsByEmail(form.getEmail())) {
            throw new UserCreationException("Email already in use");
        }
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setUsername(form.getUsername());
        userRepository.save(user);
    }

    private void validateForm(UserRegistrationForm form) throws UserCreationException {
        if (form.getEmail() == null || form.getEmail().isEmpty()) {
            throw new UserCreationException("Email is required");
        }
        if (form.getPassword() == null || form.getPassword().length() < 8) {
            throw new UserCreationException("Password must be at least 8 characters long");
        }
        if (form.getUsername() == null || form.getUsername().isEmpty()) {
            throw new UserCreationException("Username is required");
        }
        if (userRepository.existsByUsername(form.getUsername())) {
            throw new UserCreationException("Username already in use");
        }
    }
}