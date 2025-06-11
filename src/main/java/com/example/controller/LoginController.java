package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.LoginService;
import com.example.model.LoginRequest;
import com.example.model.ErrorResponse;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getUsername().isEmpty() ||
            loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty() ||
            loginRequest.getRememberMe() == null) {
            return new ResponseEntity<>(new ErrorResponse("Username, password, and rememberMe are required"), HttpStatus.BAD_REQUEST);
        }

        try {
            boolean isAuthenticated = loginService.authenticate(loginRequest);
            if (isAuthenticated) {
                return ResponseEntity.ok(new RedirectResponse("Redirect URL to landing page"));
            } else {
                return new ResponseEntity<>(new ErrorResponse("Invalid credentials"), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An unexpected error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

class RedirectResponse {
    private String redirectUrl;

    public RedirectResponse(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
}