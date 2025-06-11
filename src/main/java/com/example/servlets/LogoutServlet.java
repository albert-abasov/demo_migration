package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogoutServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LogoutServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        try {
            if (request.getUserPrincipal() != null) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect("user.jsp?message=Logout successful");
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Logout failed", e);
            response.sendRedirect("error.jsp");
        } finally {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime < 2000) {
                try {
                    Thread.sleep(2000 - elapsedTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}