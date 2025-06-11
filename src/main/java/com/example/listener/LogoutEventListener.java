package com.example.listener;

import org.springframework.context.event.EventListener;
import org.springframework.security.web.authentication.logout.LogoutSuccessEvent;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LogoutEventListener {

    private static final Logger logger = LoggerFactory.getLogger(LogoutEventListener.class);

    @EventListener
    public void handleLogout(LogoutSuccessEvent event) {
        String username = event.getAuthentication().getName();
        String sessionId = event.getSessionId();
        logger.info("User logged out: {}, Session ID: {}", username, sessionId);
    }
}