package main.webapp;

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <link rel="stylesheet" href="styles.css">
    <script>
        function validateForm() {
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;
            const email = document.getElementById("email").value;
            let valid = true;

            if (username === "") {
                document.getElementById("usernameError").innerText = "Username is required.";
                valid = false;
            } else {
                document.getElementById("usernameError").innerText = "";
            }

            if (password === "") {
                document.getElementById("passwordError").innerText = "Password is required.";
                valid = false;
            } else {
                document.getElementById("passwordError").innerText = "";
            }

            if (email === "") {
                document.getElementById("emailError").innerText = "Email is required.";
                valid = false;
            } else {
                document.getElementById("emailError").innerText = "";
            }

            return valid;
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Register</h2>
        <form action="http://yourdomain.com/RegistrationController" method="POST" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
                <span class="error" id="usernameError">${error.username}</span>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
                <span class="error" id="passwordError">${error.password}</span>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
                <span class="error" id="emailError">${error.email}</span>
            </div>
            <button type="submit">Register</button>
            <span class="success">${successMessage}</span>
        </form>
    </div>
</body>
</html>