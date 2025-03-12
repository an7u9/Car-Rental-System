<%@ page import="java.sql.*, com.carrental.config.DBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Car List</title>
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
            width: 80%;
            margin: auto;
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        button {
            padding: 8px 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
        }
        .update-btn {
            background-color: #28a745;
            color: white;
        }
        .update-btn:hover {
            background-color: #218838;
        }
        .delete-btn {
            background-color: #dc3545;
            color: white;
        }
        .delete-btn:hover {
            background-color: #c82333;
        }
        .back-link {
            display: inline-block;
            margin-top: 15px;
            padding: 8px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }
        .back-link:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="container">
        <a href="admin_dashboard.jsp" class="back-link">⬅ Go To Admin Page</a>
        <h2>🚗 Available Cars</h2>

        <table>
            <tr>
                <th>Car Number</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Year</th>
                <th>Price/Day</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>

            <%
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Cars");
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("car_number") %></td>
                <td><%= rs.getString("brand") %></td>
                <td><%= rs.getString("model") %></td>
                <td><%= rs.getInt("year") %></td>
                <td>$<%= rs.getDouble("price_per_day") %></td>
                <td><%= rs.getString("status") %></td>
                <td>
                    <a href="AddCarServlet?carNumber=<%= rs.getString("car_number") %>">
                        <button class="update-btn">Update</button>
                    </a>

                    <form action="DeleteCarServlet" method="post" style="display:inline;" 
                          onsubmit="return confirm('Are you sure you want to delete this car?');">
                        <input type="hidden" name="carNumber" value="<%= rs.getString("car_number") %>">
                        <button type="submit" class="delete-btn">Delete</button>
                    </form>
                </td>
            </tr>
            <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("<p style='color:red;'>⚠️ Error fetching cars from the database.</p>");
            }
            %>
        </table>
    </div>

</body>
</html>