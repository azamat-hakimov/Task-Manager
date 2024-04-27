<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - TaskMaster</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            text-align: center;
        }
        .error-container {
            margin-top: 100px;
        }
        h1 {
            font-size: 2rem;
            color: #ff5722; /* Red color */
        }
        p {
            font-size: 1.2rem;
            color: #555;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-align: center;
            text-decoration: none;
            font-size: 16px;
            margin-top: 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Oops! Something went wrong.</h1>
        <p>We're sorry, but there was an error processing your request.</p>
        <a href="welcome.jsp" class="btn">Go to Home page</a>
    </div>
</body>
</html>

