<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Rating</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            .container {
                width: 50%;
                margin: 50px auto;
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
            .rating-details {
                padding: 20px;
                background-color: #f9f9f9;
                border-radius: 5px;
                box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
                margin-bottom: 20px;
            }
            label {
                font-weight: bold;
                color: #555;
                display: block;
                margin-bottom: 10px;
            }
            .rating-value, .comment-value, .date-value {
                margin-bottom: 20px;
                font-size: 16px;
                color: #333;
                padding: 10px;
                background-color: #e9ecef;
                border-radius: 5px;
            }
            .back-btn {
                display: block;
                width: 100%;
                padding: 10px;
                text-align: center;
                color: white;
                background-color: #007bff;
                border: none;
                border-radius: 4px;
                text-decoration: none;
                font-size: 16px;
                cursor: pointer;
            }
            .back-btn:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Rating for Order ${order.orderID}</h2>

            <div class="rating-details">
                <label>Rating:</label>
                <div class="rating-value">${feedback.rating} / 5</div>

                <label>Comment:</label>
                <div class="comment-value">${feedback.comment}</div>

                <label>Date:</label>
                <div class="date-value">${feedback.feedbackDate}</div>
            </div>

            <a href="${goBackAction}" class="back-btn">Back to Orders</a>
        </div>
    </body>
</html>
