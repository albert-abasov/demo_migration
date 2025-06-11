package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class LogoutService {

    private final SessionManager sessionManager;
    private final TokenStore tokenStore;
    private final LogoutEventLogger logoutEventLogger;

    @Autowired
    public LogoutService(SessionManager sessionManager, TokenStore tokenStore, LogoutEventLogger logoutEventLogger) {
        this.sessionManager = sessionManager;
        this.tokenStore = tokenStore;
        this.logoutEventLogger = logoutEventLogger;
    }

    public void logout(String username) {
        if (sessionManager.sessionExists(username)) {
            sessionManager.invalidateSession(username);
        }
        String token = tokenStore.retrieveToken(username);
        if (token != null) {
            tokenStore.removeToken(username);
        }
        logoutEventLogger.logLogoutEvent(username, LocalDateTime.now());
    }
}