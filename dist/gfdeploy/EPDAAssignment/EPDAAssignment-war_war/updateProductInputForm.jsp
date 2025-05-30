<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
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
            input[type="password"],
            input[type="number"]
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
    </head>
    <body><div class="container">
            <a href="UpdateProduct" class="back-btn">&lt; Back</a>
            <h2>Update Product Form</h2>
            <form action="UpdateProductInputForm" method="POST">
                <label for="updatedProductID">Product ID:</label>
                <input type="text" id="productID" name="updatedProductID" value="${updatedProduct.productID}" readonly>
                <br><br>

                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName" value="${updatedProduct.productName}" required>
                <br><br>

                <label for="price">Price:</label>
                <input type="number" id="price" name="price" value="${updatedProduct.price}" step="0.01" required>
                <br><br>

                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" value="${updatedProduct.quantity}" required>
                <br><br>

                <input type="submit" value="Update">
            </form></div>
    </body>
</html>
