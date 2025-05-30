<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Products</title>
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
            .search-section {
                margin-bottom: 20px;
            }
            label {
                display: block;
                text-align: left;
                margin: 10px 0 5px;
                font-weight: bold;
            }
            input[type=text],
            input[type=number],
            select {
                width: 94%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 5px;
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

            .back-btn {
                display: block;
                text-align: center;
                margin-top: 20px;
                text-decoration: none;
                color: white;
                background-color: #6c757d;
                padding: 10px 15px;
                border-radius: 5px;
                transition: background-color 0.3s ease, transform 0.2s ease;
            }
            .back-btn:hover {
                background-color: #5a6268;
                transform: translateY(-2px);
            }

            .placeOrder-btn {
                display: block;
                text-align: center;
                margin-top: 20px;
                text-decoration: none;
                color: white;
                background-color: #007bff;
                padding: 10px 15px;
                border-radius: 5px;
                transition: background-color 0.3s ease, transform 0.2s ease;
            }
            .placeOrder-btn:hover {
                background-color: #0056b3;
                transform: translateY(-2px);
            }
        </style>
        <script>
            // JavaScript function for filtering products
            function filterProducts() {
                var input = document.getElementById('searchBar').value.toUpperCase();
                var filterType = document.getElementById('filterType').value;
                var table = document.getElementById('productTable');
                var tr = table.getElementsByTagName('tr');

                for (var i = 1; i < tr.length; i++) {  // Start from 1 to skip the table header
                    var td = filterType === 'name' ? tr[i].getElementsByTagName('td')[0] : tr[i].getElementsByTagName('td')[1];
                    if (td) {
                        var txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(input) > -1) {
                            tr[i].style.display = '';
                        } else {
                            tr[i].style.display = 'none';
                        }
                    }
                }
            }

            // Show quantity input field when "Add to cart" is clicked
            function showQuantityInput(productId) {
                document.getElementById('quantity-' + productId).style.display = 'inline';
                document.getElementById('addToCartBtn-' + productId).style.display = 'none'; // Hide the Add to cart button
                document.getElementById('submitBtn-' + productId).style.display = 'inline';  // Show the submit button
            }
        </script>
    </head>
    <body>
        <div class="container">
            <nav>
                <a href="Logout">Logout</a>
                <a href="EditCustomerOwnProfile">Edit individual profile</a>
                <a href="CustomerViewOwnOrderPage">Manage Orders</a>
            </nav>
            <h2>Product Page</h2>

            <!-- Search Bar -->
            <label for="searchBar">Search:</label>
            <input type="text" id="searchBar" onkeyup="filterProducts()" placeholder="Search for Product ID or Name">
            <label for="filterType">Filter By:</label>
            <select id="filterType" onchange="filterProducts()">
                <option value="name">Product Name</option>
            </select>
            <br><br>

            <!-- Product Table -->
            <table border="1" id="productTable">
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Quantity Available</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Check if products are available -->
                    <c:if test="${empty products}">
                        <tr>
                            <td colspan="4" style="text-align: center;">No items available in the shop now.</td>
                        </tr>
                    </c:if>

                    <!-- JSTL Loop to Display Products -->
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.productName}</td>
                            <td>${product.price}</td>
                            <td>${product.quantity}</td>
                            <td>
                                <form action="AddToCart" method="POST">
                                    <input type="hidden" name="productID" value="${product.productID}">
                                    <!-- Initially hidden quantity input -->
                                    <input type="number" id="quantity-${product.productID}" name="quantity" 
                                           placeholder="Enter quantity" min="1" max="${product.quantity}" 
                                           style="display: none;" required>
                                    <input type="hidden" name="productName" value="${product.productName}">
                                    <input type="hidden" name="productPrice" value="${product.price}">

                                    <!-- Add to cart button -->
                                    <button type="button" id="addToCartBtn-${product.productID}" 
                                            onclick="showQuantityInput('${product.productID}')">Add to cart</button>

                                    <!-- Submit button for form after selecting quantity -->
                                    <button type="submit" style="display: none;" id="submitBtn-${product.productID}">Confirm</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <c:if test="${not empty cartProducts}">
                <h3>Product Cart</h3>
                <ol>
                    <c:forEach var="cartProduct" items="${cartProducts}">
                        <li>${cartProduct}</li>
                        </c:forEach>
                </ol>
                <a href="UpdateCart" class="placeOrder-btn">Edit Cart / Place Order</a>
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
