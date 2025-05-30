<%-- 
    Document   : ageReport
    Created on : 28/10/2024, 9:33:09 PM
    Author     : wengk
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Age Distribution Report</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f4f4f4;
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
            .summary {
                background: #e9ecef;
                padding: 10px;
                border-radius: 5px;
                margin-bottom: 20px;
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
            #ageChart {
                max-width: 100%;
                height: 400px;
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
            <h1>Age Distribution Report</h1>
            <div class="summary">
                <h2>Summary</h2>
                <p>This report provides insights into the age distribution of users. It groups users by age ranges to show demographic representation.</p>
            </div>
            <canvas id="ageChart"></canvas>
            <div class="footer">
                <p>&copy; 2024 APU Convenience Store</p>
                <p>Report generated on: ${currentDate}</p>
            </div>
        </div>
        <script>
            const labels = [
            <c:forEach var="key" items="${ageGroups.keySet()}">
            "${key}"<c:if test="${!fn:contains(key, 'last')}">,</c:if>
            </c:forEach>
            ];

            const data = [
            <c:forEach var="value" items="${ageGroups.values()}">
                ${value}<c:if test="${!fn:contains(value, 'last')}">,</c:if>
            </c:forEach>
            ];

            const ageData = {
                labels: labels,
                datasets: [{
                        label: 'Age Distributions',
                        data: data,
                        backgroundColor: ['#36a2eb', '#ffcd56', '#ff6384', '#4bc0c0', '#9966ff'],
                        hoverOffset: 4
                    }]
            };

            new Chart(document.getElementById('ageChart'), {
                type: 'bar',
                data: ageData,
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: 'User Age Distribution'
                        }
                    },
                    scales: {
                        y: {
                            beginAtZero: true,
                            title: {
                                display: true,
                                text: 'Number of Users'
                            },
                            ticks: {
                                stepSize: 1, // Set the y-axis increment to 1
                                callback: function (value) {
                                    if (value % 1 === 0) {
                                        return value;  // Display only whole numbers
                                    }
                                }
                            }
                        }
                    }

                }
            });
        </script>
    </body>
</html>
