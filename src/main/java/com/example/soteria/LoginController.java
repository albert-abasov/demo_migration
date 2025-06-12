import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginData loginData = mapLoginData(request);
        if (validateLoginData(loginData)) {
            if (authenticateUser(loginData)) {
                response.sendRedirect("dashboard.jsp");
            } else {
                request.setAttribute("errorMessage", "Invalid credentials");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Please fill in all fields");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private LoginData mapLoginData(HttpServletRequest request) {
        LoginData loginData = new LoginData();
        loginData.setUsername(request.getParameter("username"));
        loginData.setPassword(request.getParameter("password"));
        return loginData;
    }

    private boolean validateLoginData(LoginData loginData) {
        return loginData.getUsername() != null && !loginData.getUsername().isEmpty() &&
               loginData.getPassword() != null && !loginData.getPassword().isEmpty();
    }

    private boolean authenticateUser(LoginData loginData) {
        boolean isAuthenticated = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb", "user", "password");
            String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loginData.getUsername());
            statement.setString(2, loginData.getPassword());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isAuthenticated = resultSet.getInt(1) > 0;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    }
}