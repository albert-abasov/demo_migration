<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 400px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        .error {
            color: red;
            font-size: 0.9em;
        }
        button {
            background-color: #5cb85c;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
            border-radius: 5px;
        }
        button:hover {
            background-color: #4cae4c;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Register</h2>
        <form action="register" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
                <c:if test="${not empty param.usernameError}">
                    <div class="error">${param.usernameError}</div>
                </c:if>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
                <c:if test="${not empty param.passwordError}">
                    <div class="error">${param.passwordError}</div>
                </c:if>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
                <c:if test="${not empty param.emailError}">
                    <div class="error">${param.emailError}</div>
                </c:if>
            </div>
            <c:if test="${not empty param.globalError}">
                <div class="error">${param.globalError}</div>
            </c:if>
            <button type="submit">Register</button>
        </form>
    </div>
</body>
</html>