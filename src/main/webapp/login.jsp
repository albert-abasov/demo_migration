<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <script>
        function validateForm() {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var errors = [];
            if (username === "") {
                errors.push("Username is required.");
            }
            if (password === "") {
                errors.push("Password is required.");
            }
            if (errors.length > 0) {
                var errorList = document.getElementById("errorList");
                errorList.innerHTML = "";
                errors.forEach(function(error) {
                    var li = document.createElement("li");
                    li.textContent = error;
                    errorList.appendChild(li);
                });
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h2>Login</h2>
    <ul id="errorList" style="color:red;"></ul>
    <form action="login" method="post" onsubmit="return validateForm();">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <input type="checkbox" id="rememberMe" name="rememberMe">
            <label for="rememberMe">Remember Me</label>
        </div>
        <div>
            <input type="submit" value="Login">
        </div>
    </form>
</body>
</html>