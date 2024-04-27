<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up for TaskMaster</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            text-align: center;
        }
        header {
            background-color: #4CAF50;
            color: white;
            padding: 20px 0;
        }
        h1 {
            font-size: 2.5rem;
            margin: 20px 0;
        }
        form {
            margin-top: 20px;
        }
        label {
            font-size: 1.2rem;
            font-weight: bold;
        }
        input[type="text"],
        input[type="password"] {
            padding: 10px;
            margin: 10px;
            width: 300px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 1.1rem;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 1.1rem;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        p {
            font-size: 1.1rem;
            margin-top: 20px;
        }
        a {
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <header>
        <h1>Sign Up for TaskMaster</h1>
    </header>
    <form action="<%= request.getContextPath() %>/authenticate" method="get">
        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username" required><br>
        
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br>
        
        <label for="confirm_password">Confirm Password:</label><br>
        <input type="password" id="confirm_password" name="confirm_password" required><br>
        
        <input type="hidden" name="page" value="signup">
        <input type="submit" value="Sign Up">
    </form>
    <p>Already have an account? <a href="login.jsp">Login</a></p>
</body>
</html>
