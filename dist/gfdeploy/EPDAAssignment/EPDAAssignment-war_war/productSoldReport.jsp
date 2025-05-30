<%-- 
    Document   : productSoldReport
    Created on : 28/10/2024, 11:09:51 PM
    Author     : wengk
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Product Sales and Revenue Report</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
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
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }
            th {
                background-color: #f2f2f2;
            }
            #salesChart {
                max-width: 100%;
                height: 400px;
                margin-top: 20px;
            }
            .footer {
                text-align: center;
                margin-top: 30px;
                font-size: 14px;
                color: #888;
            }
            .back-btn {
                display: inline-block;
                margin-bottom: 20px;
                padding: 8px 16px;
                background-color: #6c757d;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s;
            }
            .back-btn:hover {
                background-color: #5a6268;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <a href="generateReport.jsp" class="back-btn">&lt; Back</a>
            <h1>Product Sales and Revenue Report</h1>
            <p>This report shows the total quantity and revenue for each product sold.</p>

            <!-- Display sales data in a table -->
            <table>
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Quantity Sold</th>
                        <th>Total Revenue (RM)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${productSales}">
                        <tr>
                            <td>${entry.key}</td>
                            <td>${entry.value}</td>
                            <td>RM ${productRevenue[entry.key]}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Chart.js canvas for bar chart -->
            <canvas id="salesChart"></canvas>
            <div class="footer">
                <p>&copy; 2024 APU Convenience Store</p>
                <p>Report generated on: ${currentDate}</p>
            </div>
        </div>

        <script>
            const labels = [
            <c:forEach var="entry" items="${productSales}">
            "${entry.key}"<c:if test="${!fn:contains(entry.key, 'last')}">,</c:if>
            </c:forEach>
            ];

            const quantityData = [
            <c:forEach var="entry" items="${productSales}">
                ${entry.value}<c:if test="${!fn:contains(entry.value, 'last')}">,</c:if>
            </c:forEach>
            ];

            const revenueData = [
            <c:forEach var="entry" items="${productRevenue}">
                ${entry.value}<c:if test="${!fn:contains(entry.value, 'last')}">,</c:if>
            </c:forEach>
            ];

            const config = {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [
                        {
                            label: 'Quantity Sold',
                            data: quantityData,
                            backgroundColor: 'rgba(54, 162, 235, 0.6)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 1
                        },
                        {
                            label: 'Revenue (RM)',
                            data: revenueData,
                            backgroundColor: 'rgba(75, 192, 192, 0.6)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }
                    ]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: 'Product Sales and Revenue'
                        }
                    },
                    scales: {
                        y: {
                            beginAtZero: true,
                            title: {
                                display: true,
                                text: 'Amount'
                            },
                            ticks: {
                                stepSize: 1,
                                callback: function (value) {
                                    if (value % 1 === 0)
                                        return value;
                                }
                            }
                        }
                    }
                },
            };

            new Chart(document.getElementById('salesChart'), config);
        </script>
    </body>
</html>