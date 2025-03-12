<html>
<head>
    <title>Add Car</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 20px;
        }
        h2 {
            color: #333;
        }
        form {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
            text-align: left;
        }
        input[type="text"], 
        input[type="number"] {
            width: 100%;
            padding: 8px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #28a745;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #218838;
        }
        a {
            display: inline-block;
            margin-bottom: 15px;
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <p><a href="admin_dashboard.jsp">Go To Admin Page</a></p>
    <h2>Add New Car</h2>
    <form action="AddCarServlet" method="post">
        <label>Car Number:</label>
        <input type="text" name="car_number" required><br>
        
        <label>Brand:</label>
        <input type="text" name="brand" required><br>
        
        <label>Model:</label>
        <input type="text" name="model" required><br>
        
        <label>Year:</label>
        <input type="number" name="year" required><br>
        
        <label>Price Per Day:</label>
        <input type="number" step="0.01" name="price_per_day" required><br>
        
        
        <input type="submit" value="Add Car">
    </form>
</body>
</html>
