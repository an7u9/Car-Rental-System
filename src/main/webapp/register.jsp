<html>
<head>
    <title>User Registration</title>
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
        input[type="text"], input[type="email"], input[type="password"] {
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
        <h2>User Registration</h2>
        <form action="RegisterServlet" method="post">
            <label>Name:</label>
            <input type="text" name="name" required><br>
            
            <label>Email:</label>
            <input type="email" name="email" required><br>
            
            <label>Phone:</label>
            <input type="text" name="phone" required><br>
            
            <label>Password:</label>
            <input type="password" name="password" required><br>
            
            <label>Address:</label>
            <input type="text" name="address"><br>
            
            <input type="submit" value="Register">
        </form>

        <a href="index.jsp" class="back-link">Back to Home</a>
    </div>

</body>
</html>
