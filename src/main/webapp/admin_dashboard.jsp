<%@ page import="com.carrental.model.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Admin admin = (Admin) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect("admin_login.jsp?error=SessionExpired");
    }
%>

<html>
<head>
    <title>Admin Dashboard</title>
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
        .menu {
            margin: 20px auto;
            display: flex;
            justify-content: center;
            gap: 20px;
        }
        a {
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
            padding: 10px 20px;
            color: white;
            border-radius: 5px;
            transition: 0.3s;
        }
        a[href="add_car.jsp"] {
            background-color: #28a745; /* Green */
        }
        a[href="add_car.jsp"]:hover {
            background-color: #218838;
        }
        a[href="admin_car.jsp"] {
            background-color: #007bff; /* Blue */
        }
        a[href="admin_car.jsp"]:hover {
            background-color: #0056b3;
        }
        a[href="LogoutServlet"] {
            background-color: #dc3545; /* Red */
        }
        a[href="LogoutServlet"]:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <h2>Welcome Admin, <%= admin.getName() %>!</h2>

    <div class="menu">
        <a href="add_car.jsp">Add New Car</a>
        <a href="admin_car.jsp">View All Cars</a>
        <a href="LogoutServlet">Logout</a>
    </div>
</body>
</html>
