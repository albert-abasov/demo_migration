package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("errors", new ArrayList<String>());
        return "login";
    }

    @PostMapping("/login")
    public String handleLoginRequest(HttpServletRequest request, 
                                      HttpServletResponse response,
                                      @RequestParam String username, 
                                      @RequestParam String password, 
                                      @RequestParam(required = false) boolean rememberMe, 
                                      Model model) {
        List<String> errors = new ArrayList<>();
        
        if (username.isEmpty() || password.isEmpty()) {
            errors.add("Username and password are required.");
            model.addAttribute("errors", errors);
            return "login";
        }

        try {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("User {} logged in successfully.", username);
            return "redirect:/landing";
        } catch (Exception e) {
            errors.add("Invalid username or password.");
            logger.warn("Failed login attempt for user {}: {}", username, e.getMessage());
            model.addAttribute("errors", errors);
            return "login";
        }
    }
}