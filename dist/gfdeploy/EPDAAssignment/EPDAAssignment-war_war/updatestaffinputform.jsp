
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Staff</title>
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
            input[type="submit"] {
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
            input[type="submit"]:hover {
                background-color: #0056b3;
            }
        </style>
        <script type="text/javascript">


            function validateNRIC() {
                var nric = document.getElementById("identityCardNumber").value;
                var nricPattern = /^\d{6}-\d{2}-\d{4}$/;

                if (!nricPattern.test(nric)) {
                    alert("Invalid NRIC format. Please use YYMMDD-XX-####.");
                    return false; // Prevent form submission
                }

                var year = parseInt(nric.substring(0, 2), 10);
                var month = parseInt(nric.substring(2, 4), 10);
                var day = parseInt(nric.substring(4, 6), 10);

                // Adjust year for 1900s and 2000s
                var currentYear = new Date().getFullYear() % 100;
                year += (year > currentYear ? 1900 : 2000);

                var birthDate = new Date(year, month - 1, day);

                if (birthDate.getFullYear() !== year || birthDate.getMonth() + 1 !== month || birthDate.getDate() !== day) {
                    alert("Invalid date in NRIC. Please check the YYMMDD part.");
                    return false; // Prevent form submission
                }

                var age = new Date().getFullYear() - year;
                if (age > 100) {
                    alert("Invalid year in NRIC. Age cannot be more than 100 years.");
                    return false; // Prevent form submission
                }

                return true; // Allow form submission
            }

            function validatePhoneNumber() {
                var phoneNumber = document.getElementById("phoneNumber").value;
                var phonePattern = /^(01[0-9]-?[0-9]{7,8}|0[3-9][0-9]-?[0-9]{6,8})$/;

                if (!phonePattern.test(phoneNumber)) {
                    alert("Invalid phone number format. Please use formats like 012-3456789 or 03-1234567.");
                    return false; // Prevent form submission
                }
                return true; // Allow form submission
            }

            function validateForm() {
                return validateNRIC() && validatePhoneNumber();
            }

        </script>
    </head>
    <body>
        <div class="container">
            <a href="UpdateStaff" class="back-btn">&lt; Back</a>
            <h2>Manage Staff Form</h2>
            <form action="UpdateStaffInputForm" method="POST" onsubmit="return validateForm();">
                <!-- Username (Read-Only) -->
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="${updatedUser.username}" readonly>

                <!-- Name -->
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${updatedUser.name}" required>

                <!-- Email -->
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${updatedUser.email}" required>

                <!-- Password -->
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="${updatedUser.password}" required>

                <!-- Gender -->
                <label for="gender">Gender: </label>
                <select name="gender" id="gender" required>
                    <option value="MALE" ${updatedUser.gender == 'MALE' ? 'selected' : ''}>Male</option>
                    <option value="FEMALE" ${updatedUser.gender == 'FEMALE' ? 'selected' : ''}>Female</option>
                </select>

                <!-- Phone Number -->
                <label for="phoneNumber">Phone Number (Format: 01X-XXXXXXXX or 03-XXXXXXX): </label>
                <input type="text" name="phoneNumber" id="phoneNumber" value="${updatedUser.phoneNumber}" required 
                       pattern="^(01[0-9]-?[0-9]{7,8}|0[3-9][0-9]-?[0-9]{6,8})$" 
                       placeholder="e.g., 012-3456789 or 03-1234567">
                <br><br>

                <!-- Identity Card Number -->
                <label for="identityCardNumber">Identity Card Number (Format: YYMMDD-XX-####): </label>
                <input type="text" value="${updatedUser.identityCardNumber}"  name="identityCardNumber" id="identityCardNumber" required pattern="\d{6}-\d{2}-\d{4}" placeholder="e.g., 900101-14-1234">
                <br><br>
                
                <!-- Address -->
                <label for="address">Address: </label>
                <input type="text" value="${updatedUser.address}"  name="address" id="address">
                <br><br>

                <!-- Role -->
                <label for="role">Role:</label>
                <select id="role" name="role" required>
                    <option value="MANAGINGSTAFF" ${updatedUser.role == 'MANAGINGSTAFF' ? 'selected' : ''}>Managing Staff</option>
                    <option value="DELIVERYSTAFF" ${updatedUser.role == 'DELIVERYSTAFF' ? 'selected' : ''}>Delivery Staff</option>
                </select>

                <!-- Submit Button -->
                <br><br>
                <input type="submit" value="Update">
            </form>
        </div>
    </body>
</html>
