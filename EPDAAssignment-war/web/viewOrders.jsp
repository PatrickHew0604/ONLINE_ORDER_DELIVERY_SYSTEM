<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View All Orders</title>
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
            .no-orders {
                text-align: center;
                color: #ff0000;
                font-weight: bold;
                margin-top: 20px;
            }
            .not-rated-message {
                color: #666;
                font-style: italic;
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
        </style>
    </head>
    <body>

        <div class="container">
            <h2>All Orders</h2>

            <c:if test="${not empty orders}">
                <table>
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Customer Name</th>
                            <th>Order Date</th>
                            <th>Status</th>
                            <th>Ordered Item</th>
                            <th>Total Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td>${order.orderID}</td>
                                <td>${order.customer}</td>
                                <td>${order.orderDate}</td>
                                <td>${order.orderStatus.toString()}</td>
                                <td>
                                    <ol>
                                        <c:forEach var="product" items="${order.products}">
                                            <li>${product.matchProduct.productName} : ${product.quantity}</li>
                                        </c:forEach>
                                    </ol>
                                </td>
                                <td>
                                    <!-- Calculate total price from order items -->
                                    <c:set var="totalPrice" value="0" />
                                    <c:forEach var="product" items="${order.products}">
                                        <c:set var="totalPrice" value="${totalPrice + (product.matchProduct.price * product.quantity)}" />
                                    </c:forEach>
                                    ${totalPrice}
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${order.orderStatus == 'RATED'}">
                                            <form action="ViewOrders" method="GET">
                                                <input type="hidden" name="orderID" value="${order.orderID}">
                                                <button class="order-details-btn" type="submit">View Ratings</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="not-rated-message">Not Rated Yet</p>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${empty orders}">
                <p class="no-orders">${NoOrderMessage}</p>
            </c:if>

            <a href="managingstaffmainpage.jsp" class="back-btn">Back to Main Page</a>
        </div>

    </body>
</html>
