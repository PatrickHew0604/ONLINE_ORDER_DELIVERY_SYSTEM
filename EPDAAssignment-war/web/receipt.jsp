<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Order Receipt</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f9f9f9;
            }

            .container {
                margin: 20px auto;
                width: 80%;
                background-color: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
                text-align: center;
                margin-bottom: 20px;
                color: #333;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
                color: #333;
            }
            .total {
                text-align: right;
                font-weight: bold;
                font-size: 18px;
            }
            .center {
                text-align: center;
                margin-top: 20px;
            }
            button {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                margin: 5px;
            }
            button:hover {
                background-color: #45a049;
            }
            .btn-secondary {
                background-color: #f44336;
            }
            .btn-secondary:hover {
                background-color: #d32f2f;
            }
        </style>
    </head>
    <body>


        <div class="container">
            <h1>Order Receipt</h1>
            <p><strong>Order ID:</strong> ${order.orderID}</p>
            <p><strong>Customer:</strong> ${order.customer.name}</p>
            <p><strong>Order Date:</strong> ${order.orderDate}</p>

            <h2>Ordered Items</h2>
            <table>
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${order.products}">
                        <tr>
                            <td>${product.matchProduct.productName}</td>
                            <td>${product.quantity}</td>
                            <td>${product.matchProduct.price}</td>
                            <td>${product.matchProduct.price * product.quantity}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <p class="total">Total Price: <strong>${totalPrice}</strong></p>

            <div class="center">
                <button onclick="window.print()">Print Receipt</button>
                <form action="PaymentReceivedServlet" method="post" style="display: inline;">
                    <input type="hidden" name="orderID" value="${order.orderID}">
                    <button class="btn-secondary" 
                            type="submit" 
                            onclick="return confirm('Are you sure the payment has been received?')">Payment Received</button>
                </form>
            </div>
        </div>

    </body>
</html>
