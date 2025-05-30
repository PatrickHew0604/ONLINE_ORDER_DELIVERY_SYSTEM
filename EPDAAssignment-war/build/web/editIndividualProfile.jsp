<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Individual Profile</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f0f2f5;
                margin: 0;
                padding: 0;
            }
            .container {
                background-color: #fff;
                width: 50%;
                margin: 40px auto;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
            }
            .back-btn {
                text-decoration: none;
                color: white;
                background-color: #6c757d;
                padding: 10px 15px;
                border-radius: 5px;
                transition: background-color 0.3s ease, transform 0.2s ease;
                display: inline-block;
                margin-bottom: 20px;
            }
            .back-btn:hover {
                background-color: #5a6268;
                transform: translateY(-2px);
            }
            h2 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }
            label {
                display: block;
                margin-bottom: 5px;
                color: #555;
            }
            input[type="text"],
            input[type="email"],
            input[type="password"]
            {
                width: calc(100% - 20px);
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
            }

            select {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
            }
            input[type="submit"],button {
                padding: 10px 15px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s ease;
                width: 100%;
            }
            input[type="submit"]:hover, button:hover {
                background-color: #0056b3;
            }
        </style>
        <script type="text/javascript">
            function enablePasswordEdit(button) {
                var storedPassword = "${updatedUser.password}"; // Password from the backend
                var enteredPassword = prompt("Please enter your current password:");

                if (enteredPassword === storedPassword) {
                    document.getElementById("password").disabled = false;
                    button.disabled = true; // Disable the button after successful verification
                    alert("You can now change your password.");
                } else {
                    alert("Incorrect password. You cannot change the password.");
                }
            }
        </script>
    </head>
    <body><div class="container">
            <a href="${href}" class="back-btn">&lt; Back</a>
            <h2>Edit Individual Profile</h2>
            <form action="${action}" method="POST">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="${updatedUser.username}" readonly>
                <br><br>

                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${updatedUser.name}" required>
                <br><br>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${updatedUser.email}" required>
                <br><br>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="${updatedUser.password}" disabled required>
                <input type="hidden" id="hiddenPassword" name="hiddenPassword" value="${updatedUser.password}">

                <button type="button" onclick="enablePasswordEdit(this)">Verify Password</button>
                <br><br>

                <!-- Gender -->
                <label for="gender">Gender: </label>
                <select name="gender" id="gender" required>
                    <option value="MALE" ${updatedUser.gender == 'MALE' ? 'selected' : ''}>Male</option>
                    <option value="FEMALE" ${updatedUser.gender == 'FEMALE' ? 'selected' : ''}>Female</option>
                </select>
                <br><br>

                <!-- Phone Number -->
                <label for="phoneNumber">Phone Number: </label>
                <input type="text" name="phoneNumber" id="phoneNumber" value="${updatedUser.phoneNumber}" required>
                <br><br>

                <!-- Identity Card Number -->
                <label for="identityCardNumber">Identity Card Number: </label>
                <input type="text" name="identityCardNumber" id="identityCardNumber" value="${updatedUser.identityCardNumber}" required>
                <br><br>

                <!-- Address -->
                <label for="address">Address: </label>
                <input type="text" name="address" id="address" value="${updatedUser.address}" required>
                <br><br>

                <input type="submit" value="Update">
            </form></div>
    </body>
</html>
