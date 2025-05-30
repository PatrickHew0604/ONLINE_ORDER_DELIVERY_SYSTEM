<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Give Rating</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            .container {
                width: 40%;
                margin: 50px auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                text-align: center;
                color: #333;
                margin-bottom: 30px;
            }
            .rating-form {
                display: flex;
                flex-direction: column;
                gap: 15px;
            }
            label {
                font-weight: bold;
                margin-bottom: 5px;
                color: #555;
            }
            input[type="number"] {
                width: 60px;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
                text-align: center;
            }
            textarea {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 4px;
                resize: none;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                font-size: 14px;
            }
            .submit-btn {
                background-color: #28a745;
                color: white;
                padding: 12px;
                border: none;
                border-radius: 4px;
                font-size: 16px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .submit-btn:hover {
                background-color: #218838;
            }
            .back-btn {
                display: block;
                text-align: center;
                margin-top: 20px;
                color: #007bff;
                text-decoration: none;
                font-size: 16px;
            }
            .back-btn:hover {
                text-decoration: underline;
            }
            @media (max-width: 768px) {
                .container {
                    width: 80%;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Give a Rating for Order ${order.orderID}</h2>

            <form action="GiveRating" method="POST" class="rating-form">
                <input type="hidden" name="orderID" value="${order.orderID}">
                <input type="hidden" name="customerID" value="${loginUser.username}">
                
                <div>
                    <label for="rating">Rating (1-5):</label>
                    <input type="number" id="rating" name="rating" min="1" max="5" required>
                </div>
                
                <div>
                    <label for="comment">Comment:</label>
                    <textarea id="comment" name="comment" placeholder="Write your feedback here..." required></textarea>
                </div>
                
                <button type="submit" class="submit-btn">Submit Rating</button>
            </form>

            <a href="CustomerViewOwnOrderPage" class="back-btn">Back to Orders</a>
        </div>
    </body>
</html>
