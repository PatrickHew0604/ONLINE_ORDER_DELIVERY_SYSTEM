<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Order</title>
    </head>
    <body>
        <a href="CustomerMainPage">Back</a>
        <h2>Edit Your Order</h2>

        <c:if test="${not empty order.products}">
            <!-- Order Table -->
            <form action="EditCustomerOwnOrder" method="POST">
                <input type="hidden" name="orderID" value="${order.orderID}">
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
                        <c:forEach var="product" items="${order.products}">
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

                                    <!-- Button to remove item from order -->
                                    <button type="submit" name="action" value="remove-${product.matchProduct.productID}">Remove</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>

            <br>
            <h3>Total Price: ${totalPrice}</h3>
            <button onclick="location.href = 'CustomerViewOrderPage'">Done</button>
        </c:if>

    </body>
</html>
