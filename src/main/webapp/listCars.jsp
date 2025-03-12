<%@ page import="java.sql.*, com.carrental.config.DBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
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
        .rent-button {
            padding: 8px 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }
        .available {
            background-color: #28a745;
            color: white;
        }
        .available:hover {
            background-color: #218838;
        }
        .rented {
            background-color: gray;
            color: white;
            cursor: not-allowed;
        }
    </style>
</head>
<body>

    <div class="container">
        <p><a href="dashboard.jsp">Go To Your Dashboard</a></p>
        <h2>Available Cars 🚘</h2>

        <table>
            <tr>
                <th>Car Number</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Year</th>
                <th>Price/Day</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <%
                try (Connection conn = DBConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Cars");
                     ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        String status = rs.getString("status");
            %>
            <tr>
                <td><%= rs.getString("car_number") %></td>
                <td><%= rs.getString("brand") %></td>
                <td><%= rs.getString("model") %></td>
                <td><%= rs.getInt("year") %></td>
                <td>$<%= rs.getDouble("price_per_day") %></td>
                <td><%= status %></td>
                <td>
                    <% if ("rented".equalsIgnoreCase(status)) { %>
                        <button class="rent-button rented" disabled>Rented</button>
                    <% } else { %>
                        <a href="rentCar.jsp?carNumber=<%= rs.getString("car_number") %>">
                            <button class="rent-button available">Rent</button>
                        </a>
                    <% } %>
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
