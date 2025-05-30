<%-- 
    Document   : customerSpendingReport
    Created on : 29/10/2024, 12:18:14 AM
    Author     : wengk
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Customer Spending Report</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                margin: 0;
                padding: 20px;
            }
            .container {
                max-width: 800px;
                margin: auto;
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
                text-align: center;
                color: #333;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #f2f2f2;
            }
            .high-value {
                color: #4caf50;
                font-weight: bold;
            }
            .back-btn {
                top: 20px; /* You can keep this */
                left: 20px; /* You can keep this */
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
            .footer {
                text-align: center;
                margin-top: 30px;
                font-size: 14px;
                color: #888;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <a href="generateReport.jsp" class="back-btn">&lt; Back</a>
            <h1>Customer Spending Report</h1>
            <table>
                <thead>
                    <tr>
                        <th>Customer Name</th>
                        <th>Total Spending (RM)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${customerSpending}">
                        <tr>
                            <td>${entry.key.name}</td>
                            <td class="${entry.value >= 500 ? 'high-value' : ''}">
                                RM ${entry.value}
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty customerSpending}">
                        <tr>
                            <td colspan="2">No data available.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
            <div class="footer">
                <p>&copy; 2024 APU Convenience Store</p>
                <p>Report generated on: ${currentDate}</p>
            </div>
        </div>
    </body>
</html>

