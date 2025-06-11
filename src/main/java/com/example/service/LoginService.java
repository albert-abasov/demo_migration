package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public String authenticate(LoginRequest loginRequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            if (authentication.isAuthenticated()) {
                return "Authentication successful";
            }
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Invalid username or password");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred during authentication");
        }
        throw new InvalidCredentialsException("Authentication failed");
    }
}