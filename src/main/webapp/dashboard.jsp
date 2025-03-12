<%@ page import="com.carrental.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User usr = (User) session.getAttribute("user");
    if (usr == null) {
        response.sendRedirect("login.jsp");
    }
%>

<html>
<head>
    <title>Dashboard</title>
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
        }
        h2 {
            color: #333;
        }
        .links {
            margin-top: 20px;
        }
        .links a {
            text-decoration: none;
            color: white;
            background-color: #007bff;
            padding: 10px 15px;
            border-radius: 5px;
            margin: 5px;
            display: inline-block;
            font-weight: bold;
        }
        .links a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Welcome, <%= usr.getName() %>!</h2>
        <div class="links">
            <a href="listCars.jsp">View Available Cars</a>
            <a href="LogoutServlet">Logout</a>
        </div>
    </div>

</body>
</html>
