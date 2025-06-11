package com.example.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);
    private static final String AUTH_TOKEN = "AUTH_TOKEN";

    @GetMapping("/user/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userId = (String) session.getAttribute("userId");
            session.invalidate();
            if (userId != null) {
                logger.info("User {} logged out", userId);
            }
        }
        Cookie authTokenCookie = new Cookie(AUTH_TOKEN, null);
        authTokenCookie.setMaxAge(0);
        response.addCookie(authTokenCookie);
        try {
            response.sendRedirect("/login");
        } catch (Exception e) {
            logger.error("Error during redirecting to login page", e);
        }
        return null;
    }
}