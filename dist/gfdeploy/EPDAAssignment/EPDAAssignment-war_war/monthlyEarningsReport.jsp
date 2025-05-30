<%-- 
    Document   : monthlyEarningsReport
    Created on : 29/10/2024, 12:07:00 AM
    Author     : wengk
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Monthly Earnings Report</title>
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
            #earningsChart {
                max-width: 100%;
                height: 400px;
                margin-top: 20px;
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
            <h1>Monthly Earnings Report</h1>
            <p>This report shows the total earnings for each month.</p>

            <!-- Display earnings data in a table -->
            <table>
                <thead>
                    <tr>
                        <th>Month</th>
                        <th>Total Earnings (RM)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${monthlyEarnings}">
                        <tr>
                            <td>${entry.key}</td>
                            <td>RM ${entry.value}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Chart.js canvas for bar chart -->
            <canvas id="earningsChart"></canvas>
            <div class="footer">
                <p>&copy; 2024 APU Convenience Store</p>
                <p>Report generated on: ${currentDate}</p>
            </div>
        </div>

        <script>
            const labels = [
            <c:forEach var="entry" items="${monthlyEarnings}">
            "${entry.key}"<c:if test="${!fn:contains(entry.key, 'last')}">,</c:if>
            </c:forEach>
            ];

            const earningsData = [
            <c:forEach var="entry" items="${monthlyEarnings}">
                ${entry.value}<c:if test="${!fn:contains(entry.value, 'last')}">,</c:if>
            </c:forEach>
            ];

            const config = {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                            label: 'Total Earnings (RM)',
                            data: earningsData,
                            backgroundColor: 'rgba(75, 192, 192, 0.6)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: 'Monthly Earnings'
                        }
                    },
                    scales: {
                        y: {
                            beginAtZero: true,
                            title: {
                                display: true,
                                text: 'Earnings (RM)'
                            },
                            ticks: {
                                stepSize: 1000,
                                callback: function (value) {
                                    if (value % 1 === 0)
                                        return value;
                                }
                            }
                        }
                    }
                },
            };

            new Chart(document.getElementById('earningsChart'), config);
        </script>
    </body>
</html>
