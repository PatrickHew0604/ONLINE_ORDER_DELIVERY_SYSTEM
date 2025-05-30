<%-- 
    Document   : login
    Created on : 11/10/2024, 12:25:44 PM
    Author     : wengk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            .container {
                width: 30%;
                margin: 100px auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }
            label {
                font-weight: bold;
                color: #555;
                display: block;
                margin-bottom: 10px;
            }
            input[type="text"], input[type="password"] {
                width: 97%;
                padding: 10px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            input[type="submit"] {
                background-color: #007bff;
                color: white;
                padding: 10px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                width: 100%;
            }
            input[type="submit"]:hover {
                background-color: #0056b3;
            }
            .register-link {
                display: block;
                text-align: center;
                margin-top: 20px;
            }
            .register-link a {
                text-decoration: none;
                color: #007bff;
            }
            .register-link a:hover {
                color: #0056b3;
            }
        </style>

    </head>
    <body>
        <div class="container">
            <h2>Login</h2>
            <form action="Login" method="GET" onsubmit="return validatePasswords();">
                <label for="username">Username:</label>
                <input type="text" name="username" id="username" required>

                <label for="password">Password:</label>
                <input type="password" name="password" id="password" required>

                <input type="submit" value="Login">
            </form>

            <div class="register-link">
                <a href="register.jsp">New User? Register here</a>
            </div>
        </div>
    </body>
    <%
        String displayMessage = (String) request.getAttribute("displayMessage");
        if (displayMessage != null) {
    %>
    <script>
        alert("<%= displayMessage%>");
    </script>
    <%
        }
    %>
</html>
