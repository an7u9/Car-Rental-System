<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Car</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
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
            display: block;
            margin-top: 10px;
        }
        input[type="text"], input[type="number"], select {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            background-color: #007bff;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
            margin-top: 10px;
        }
        button:hover {
            background-color: #0056b3;
        }
        .back-link {
            display: inline-block;
            margin-top: 15px;
            padding: 8px 15px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }
        .back-link:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>🚗 Update Car</h2>
        <form action="AddCarServlet" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="car_number" value="<%= request.getAttribute("carNumber") %>">

            <label>Brand:</label>
            <input type="text" name="brand" value="<%= request.getAttribute("brand") %>" required>

            <label>Model:</label>
            <input type="text" name="model" value="<%= request.getAttribute("model") %>" required>

            <label>Year:</label>
            <input type="number" name="year" value="<%= request.getAttribute("year") %>" required>

            <label>Price per Day:</label>
            <input type="number" name="price_per_day" value="<%= request.getAttribute("pricePerDay") %>" step="0.01" required>

            <label>Status:</label>
            <select name="status">
                <option value="Available">Available</option>
                <option value="Rented">Rented</option>
            </select>

            <button type="submit">Update Car</button>
        </form>

        <a href="dashboard.jsp" class="back-link">⬅ Back to Dashboard</a>
    </div>

</body>
</html>
