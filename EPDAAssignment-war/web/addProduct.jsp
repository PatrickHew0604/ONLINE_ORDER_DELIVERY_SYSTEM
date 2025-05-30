<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Management</title>
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
            .back-btn {
                display: inline-block;
                margin-bottom: 20px;
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
            .toggle-label {
                display: block;
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
        </style>
        <script>
            // JavaScript function for filtering products
            function filterProducts() {
                var input = document.getElementById('searchBar').value.toUpperCase();
                var filterType = document.getElementById('filterType').value;
                var table = document.getElementById('productTable');
                var tr = table.getElementsByTagName('tr');

                for (var i = 1; i < tr.length; i++) { // Start from 1 to skip the table header
                    var td = filterType === 'name' ? tr[i].getElementsByTagName('td')[1] : tr[i].getElementsByTagName('td')[0];
                    if (td) {
                        var txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(input) > -1) {
                            tr[i].style.display = ''; // Show row
                        } else {
                            tr[i].style.display = 'none'; // Hide row
                        }
                    }
                }
            }

            // JavaScript function to toggle the visibility of search bar and table
            function toggleVisibility() {
                var searchSection = document.getElementById('searchSection');
                var tableSection = document.getElementById('tableSection');
                if (searchSection.style.display === 'none') {
                    searchSection.style.display = 'block';
                    tableSection.style.display = 'block';
                } else {
                    searchSection.style.display = 'none';
                    tableSection.style.display = 'none';
                }
            }
        </script>
    </head>
    <body>
        <div class="container">
            <a href="manageProduct.jsp" class="back-btn">&lt; Back</a>
            <h2>Product Management</h2>

            <!-- Show/Hide toggle for the product table and search -->
            <label class="toggle-label">
                <input type="checkbox" id="toggleTable" onclick="toggleVisibility()"> Show/Hide Products
            </label>

            <!-- Search section for filtering products -->
            <div id="searchSection" style="display:block;" class="search-section">
                <label for="searchBar">Search:</label>
                <input type="text" id="searchBar" onkeyup="filterProducts()" placeholder="Search by Product ID or Name">
                <label for="filterType">Filter By:</label>
                <select id="filterType" onchange="filterProducts()">
                    <option value="id">Product ID</option>
                    <option value="name">Product Name</option>
                </select>
            </div>

            <!-- Table displaying the list of products -->
            <div id="tableSection" style="display:block;">
                <table border="1" id="productTable">
                    <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Check if products are available -->
                        <c:if test="${empty products}">
                            <tr>
                                <td colspan="4" class="no-items">No items available in the shop now.</td>
                            </tr>
                        </c:if>
                        <!-- Using JSTL to loop through the products passed from the servlet -->
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.productID}</td>
                                <td>${product.productName}</td>
                                <td>${product.price}</td>
                                <td>${product.quantity}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <br>

            <!-- Form to add a new product -->
            <h2>Add New Product</h2>
            <form action="AddProduct" method="POST">
                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName" required>
                <br><br>

                <label for="price">Price:</label>
                <input type="number" id="price" name="price" step="0.01" required>
                <br><br>

                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" required>
                <br><br>

                <input type="submit" value="Add Product">
            </form>
        </div>
    </body>
</html>
