<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Gender Report</title>
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
                position: relative; /* Add this line */
            }
            h1 {
                text-align: center;
                color: #333;
            }
            h2 {
                color: #555;
            }
            .footer {
                text-align: center;
                margin-top: 30px;
                font-size: 14px;
                color: #888;
            }
            #genderChart {
                max-width: 50%;
                height: auto;
                margin: auto;
            }
            .summary {
                background: #e9ecef;
                padding: 10px;
                border-radius: 5px;
                margin-bottom: 20px;
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
        </style>
    </head>
    <body>
        <div class="container">
            <a href="generateReport.jsp" class="back-btn">&lt; Back</a>
            <h1>Gender Distribution Report</h1>
            <div class="summary">
                <h2>Summary</h2>
                <p>This report presents the distribution of gender among the users. The data is gathered from user registrations and provides insights into the gender representation.</p>
            </div>
            <canvas id="genderChart" width="400" height="400"></canvas>
            <div class="footer">
                <p>&copy; 2024 APU Convenience Store</p>
                <p>Report generated on: ${currentDate}</p>
            </div>
        </div>
        <script>
            const labels = [
            <c:forEach var="key" items="${genderCounts}">
            "${key}"<c:if test="${!fn:contains(key, 'last')}">,</c:if>
            </c:forEach>
            ];

            const data = [
            <c:forEach var="value" items="${genderCounts}">
                ${value}<c:if test="${!fn:contains(value, 'last')}">,</c:if>
            </c:forEach>
            ];

            const genderData = {
                labels: labels,
                datasets: [{
                        label: 'Gender Distribution',
                        data: data,
                        backgroundColor: ['#ff6384', '#36a2eb'],
                        hoverOffset: 4
                    }]
            };

            new Chart(document.getElementById('genderChart'), {
                type: 'pie',
                data: genderData,
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: 'User Gender Distribution'
                        }
                    }
                }
            });
        </script>
    </body>
</html>
