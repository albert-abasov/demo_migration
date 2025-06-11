package com.example.soteria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.soteria.service.AuthService;
import com.example.soteria.model.LoginRequest;
import com.example.soteria.model.SessionResponse;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isValid = authService.validateCredentials(loginRequest);
        if (isValid) {
            return ResponseEntity.status(HttpStatus.OK).body("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/validateSession")
    public ResponseEntity<SessionResponse> validateSession(@RequestHeader("Session-ID") String sessionId) {
        boolean isValid = authService.checkSessionValidity(sessionId);
        if (isValid) {
            return ResponseEntity.status(HttpStatus.OK).body(new SessionResponse("Session is valid"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new SessionResponse("Invalid session"));
        }
    }
}