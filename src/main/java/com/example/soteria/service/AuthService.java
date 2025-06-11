package com.example.soteria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public boolean authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Transactional
    public String createSession(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            Session session = new Session();
            session.setUser(userOptional.get());
            session.setCreatedAt(LocalDateTime.now());
            session.setExpiresAt(LocalDateTime.now().plusHours(1));
            sessionRepository.save(session);
            return session.getId();
        }
        throw new EntityNotFoundException("User not found");
    }

    @Transactional(readOnly = true)
    public boolean isValidSession(String sessionId) {
        Optional<Session> sessionOptional = sessionRepository.findById(sessionId);
        return sessionOptional.isPresent() && sessionOptional.get().getExpiresAt().isAfter(LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public Session getSessionDetails(String sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(() -> new EntityNotFoundException("Session not found"));
    }
}