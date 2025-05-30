<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Staff Page</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f0f2f5;
                margin: 0;
                padding: 0;
            }
            .container {
                background-color: #fff;
                width: 400px;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
                text-align: center;
                margin: 80px auto; /* Center the container and provide top margin for visibility */
                position: relative; /* Allows absolute positioning of the back button */
            }
            .back-btn {
                position: absolute; /* Position it within the container */
                top: 20px;
                left: 20px;
                font-size: 16px;
                text-decoration: none;
                color: white;
                background-color: #6c757d; /* Grey color for the button */
                padding: 10px 15px; /* Padding for a better button shape */
                border-radius: 5px; /* Rounded corners */
                transition: background-color 0.3s ease, transform 0.2s ease; /* Smooth transitions */
            }
            .back-btn:hover {
                background-color: #5a6268; /* Darker grey on hover */
                transform: translateY(-2px); /* Slight upward movement on hover */
            }
            h2 {
                color: #333;
                margin-bottom: 20px;
                font-size: 24px;
                margin-top: 60px; /* Add top margin to avoid overlap with the back button */
            }
            ol {
                padding: 0;
                list-style: none;
                text-align: left;
            }
            li {
                margin-bottom: 15px;
            }
            a {
                display: block;
                background-color: #007bff;
                color: white;
                padding: 12px;
                border-radius: 6px;
                font-size: 16px;
                text-decoration: none;
                transition: background-color 0.3s ease;
            }
            a:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <a href="managingstaffmainpage.jsp" class="back-btn">&lt; Back</a>
            <h2>Generate Reports</h2>
            <ol>
                <li><a href="GenderReport">Customer Gender Reports</a></li>
                <li><a href="AgeReport">Customer Age Report</a></li>
                <li><a href="CustomerSpendingReport">Customer Spending Report</a></li>
                <li><a href="ProductSoldReport">Product Sold Report</a></li>
                <li><a href="MonthlyEarningsReport">Month Earning Report</a></li>
            </ol>
        </div>
    </body>
</html>
