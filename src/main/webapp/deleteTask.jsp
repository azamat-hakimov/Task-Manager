<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Task - TaskMaster</title>
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
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        h1 {
            font-size: 2rem;
            margin: 20px auto;
        }
        .form-container {
            width: 60%;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #ff5722;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 1.1rem;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #f44336;
        }
        .back-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 1.1rem;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
        }
        .back-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <header>
        <h1>Delete Task - TaskMaster</h1>
        <a href="myTasks.jsp" class="back-button">Back to My Tasks</a>
    </header>
    <div class="form-container">
        <form action="<%= request.getContextPath() %>/delete" method="post">
            <label for="taskName">Task Name:</label>
            <input type="text" id="taskName" name="taskName" required><br>
            <input type="submit" value="Delete Task">
        </form>
    </div>
</body>
</html>

