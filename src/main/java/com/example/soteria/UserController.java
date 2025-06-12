package com.example.soteria;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/user")
public class UserController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            UserAccount userAccount = UserAccountDAO.getUserAccountByUsername(username);
            if (userAccount != null) {
                request.setAttribute("userAccount", userAccount);
                request.getRequestDispatcher("user.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "The user account associated with the provided username was not found.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @WebServlet("/logout")
    protected void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}