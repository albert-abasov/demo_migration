package com.example.logger;

import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogoutEventLogger {
    private static final Logger logger = Logger.getLogger(LogoutEventLogger.class.getName());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        try {
            Handler fileHandler = new FileHandler("logout_events.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to initialize logger", e);
        }
    }

    public void logLogoutEvent(String username) {
        String timestamp = dateFormat.format(new Date());
        logger.info("User: " + username + " logged out at: " + timestamp);
    }
}