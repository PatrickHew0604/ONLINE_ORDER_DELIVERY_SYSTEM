<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f0f2f5;
                margin: 0;
                padding: 0;
            }
            .container {
                background-color: #fff;
                width: 80%;
                margin: 40px auto;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
            }
            h2 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }
            nav {
                background-color: #007bff;
                padding: 10px;
                border-radius: 5px;
                margin-bottom: 20px;
            }
            nav a {
                color: white;
                text-decoration: none;
                padding: 10px 15px;
                margin: 0 10px;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }
            nav a:hover {
                background-color: #0056b3;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
            }
            th, td {
                padding: 12px;
                text-align: left;
                border: 1px solid #ddd;
            }
            th {
                background-color: #007bff;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            button {
                padding: 6px 12px;
                background-color: #007bff; /* Bootstrap primary color */
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            button:hover {
                background-color: #0069d9; /* Darker shade on hover */
            }
            .no-items {
                text-align: center;
                color: #ff0000;
                font-weight: bold;
                margin-top: 20px;
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

            input[type="number"]
            {
                width: calc(100% - 20px);
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
    </head>
    <body>
        <div class="container">
            <nav>
                <a href="Logout">Logout</a>
                <a href="EditCustomerOwnProfile">Edit individual profile</a>
                <a href="CustomerViewOwnOrderPage">Manage Orders</a>
            </nav>

            <a class="back-btn" href="CustomerMainPage">&lt; Back</a>
            <h2>Your Shopping Cart</h2>

            <c:if test="${not empty shoppingCartProducts}">
                <!-- Shopping Cart Table -->
                <form action="UpdateCart" method="POST">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Product ID</th>
                                <th>Product Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="product" items="${shoppingCartProducts}">
                                <tr>
                                    <td>${product.matchProduct.productID}</td>
                                    <td>${product.matchProduct.productName}</td>
                                    <td>${product.matchProduct.price}</td>
                                    <td>
                                        <input type="number" name="quantity-${product.matchProduct.productID}" value="${product.quantity}" min="1" required>
                                    </td>
                                    <td>${product.matchProduct.price * product.quantity}</td>
                                    <td>
                                        <!-- Button to update quantity -->
                                        <button type="submit" name="action" value="update-${product.matchProduct.productID}">Update</button>

                                        <!-- Button to remove item from cart -->
                                        <button type="submit" name="action" value="remove-${product.matchProduct.productID}">Remove</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form>

                <br>
                <h3>Total Price: ${totalPrice}</h3>
                <input type="submit" onclick="location.href = 'PlaceOrder'" class="placeOrder-btn" value="Place Order">
            </c:if>

            <c:if test="${empty shoppingCartProducts}">
                <p class="no-items">Your shopping cart is empty.</p>
            </c:if>
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
