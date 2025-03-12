<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rent a Car</title>
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
            width: 350px;
            margin: auto;
        }
        h2 {
            color: #333;
        }
        form {
            text-align: left;
        }
        label {
            font-weight: bold;
        }
        input[type="text"], input[type="email"], input[type="date"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .dashboard-link {
            display: inline-block;
            margin-top: 15px;
            padding: 8px 15px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }
        .dashboard-link:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>🚗 Rent a Car</h2>
        <form action="BookCarServlet" method="post">
            <input type="hidden" name="carNumber" value="<%= request.getParameter("carNumber") %>">
            
            <label>User Email:</label> 
            <input type="email" name="userEmail" required><br>
            
            <label>Pickup Date:</label> 
            <input type="date" name="pickupDate" required><br>
            
            <label>Return Date:</label> 
            <input type="date" name="returnDate" required><br>
            
            <input type="submit" value="Book Now">
        </form>

        <a href="dashboard.jsp" class="dashboard-link">⬅ Go To Your Dashboard</a>
    </div>

</body>
</html>
