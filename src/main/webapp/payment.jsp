<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment Invoice</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            text-align: center;
            padding: 20px;
        }
        .container {
            background: white;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            margin: auto;
        }
        h2, h3 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        td {
            background-color: #f1f1f1;
        }
        .success {
            color: green;
            font-weight: bold;
        }
        .button {
            display: inline-block;
            margin-top: 15px;
            padding: 10px 15px;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            border: none;
            cursor: pointer;
        }
        .back-link {
            background-color: #28a745;
        }
        .back-link:hover {
            background-color: #218838;
        }
        .download-btn {
            background-color: #007bff;
        }
        .download-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>💳 Payment Invoice</h2>
        
        <%
            String userEmail = request.getParameter("userEmail");
            String carNumber = request.getParameter("carNumber");
            String pickupDate = request.getParameter("pickupDate");
            String returnDate = request.getParameter("returnDate");
            String totalCost = request.getParameter("totalPrice");
            String bookingId = request.getParameter("bookingId"); // Ensure this is passed from the servlet
        %>

        <table>
            <tr>
                <th>Your Email</th>
                <td><%= userEmail %></td>
            </tr>
            <tr>
                <th>Booked Car Number</th>
                <td><%= carNumber %></td>
            </tr>
            <tr>
                <th>Pickup Date</th>
                <td><%= pickupDate %></td>
            </tr>
            <tr>
                <th>Return Date</th>
                <td><%= returnDate %></td>
            </tr>
            <tr>
                <th>Total Expense</th>
                <td>$<%= totalCost %></td>
            </tr>
        </table>

        <h3 class="success">✅ Payment Successful</h3>
        <h4>Thank you for booking with us!</h4>

        <!-- Download Invoice Button -->
		<a href="DownloadInvoiceServlet?bookingId=<%= bookingId %>" class="button download-btn">Download Invoice</a>
        <!-- Back to Profile Button -->
        <a href="dashboard.jsp" class="button back-link">Go Back to Your Profile</a>
    </div>

</body>
</html>
