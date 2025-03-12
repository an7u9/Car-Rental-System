<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car Rental System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 20px;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
            width: 80%;
        }
        h2 {
            color: #333;
        }
        p a {
            text-decoration: none;
            color: white;
            background-color: #007bff;
            padding: 10px 15px;
            border-radius: 5px;
            margin: 5px;
            display: inline-block;
            font-weight: bold;
        }
        p a:hover {
            background-color: #0056b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background: white;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Welcome to Car Rental System 🚗</h2>
        
        <p>
            <a href="register.jsp">Register</a> 
            <a href="login.jsp">User Login</a> 
            <a href="admin_login.jsp">Admin Login</a>
        </p>

        <h3>All Cars</h3>
        <table>
            <tr>
                <th>Car Number</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Year</th>
                <th>Price per Day</th>
                <th>Status</th>
            </tr>

            <%
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    java.sql.Connection conn = java.sql.DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/car", "root", "anshu@12");
                    java.sql.Statement stmt = conn.createStatement();
                    java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Cars");
                    
                    while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("car_number") %></td>
                <td><%= rs.getString("brand") %></td>
                <td><%= rs.getString("model") %></td>
                <td><%= rs.getInt("year") %></td>
                <td>$<%= rs.getDouble("price_per_day") %></td>
                <td><%= rs.getString("status") %></td>
            </tr>
            <%
                    }
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
        </table>
    </div>

</body>
</html>