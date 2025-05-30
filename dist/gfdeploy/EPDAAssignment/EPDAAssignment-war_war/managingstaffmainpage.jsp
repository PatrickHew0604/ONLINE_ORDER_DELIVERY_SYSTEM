<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Managing Staff Main Page</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f0f2f5;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .container {
                background-color: #fff;
                width: 400px;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
                text-align: center;
            }
            h2 {
                color: #333;
                margin-bottom: 20px;
                font-size: 24px;
            }
            ol {
                padding: 0;
                list-style: none;
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
            .logout-btn {
                background-color: #dc3545;
            }
            .logout-btn:hover {
                background-color: #c82333;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Managing Staff</h2>
            <ol>
                <li><a href="managestaff.jsp">Manage Staff</a></li>
                <li><a href="managecustomer.jsp">Manage Customer</a></li>
                <li><a href="manageProduct.jsp">Manage Product</a></li>
                <li><a href="UpdateOrderStatus">Update Order Status / Assign Order To Delivery Staff</a></li>
                <li><a href="ViewOrders">View All Orders</a></li>
                <li><a href="generateReport.jsp">Generate Reports</a></li>
                <li><a href="Logout" class="logout-btn">Logout</a></li>
            </ol>
        </div>
    </body>
</html>
