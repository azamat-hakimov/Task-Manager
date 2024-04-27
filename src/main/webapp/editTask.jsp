<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Task - TaskMaster</title>
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
        h2 {
            font-size: 2rem;
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
        textarea,
        input[type="date"],
        select {
            padding: 10px;
            margin: 10px;
            width: 300px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 1.1rem;
        }
        select {
            width: 314px; /* Adjusted width to align with other inputs */
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
        .my-tasks-link {
            position: absolute;
            top: 20px;
            right: 20px;
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
            border: 2px solid #4CAF50;
            border-radius: 5px;
            padding: 10px 20px;
            transition: all 0.3s ease;
            background-color: white; /* Default background color */
        }
        .my-tasks-link:hover {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
    <header>
        <h2>Edit Task - TaskMaster</h2>
    </header>
    <form action="<%= request.getContextPath() %>/edit" method="post">
        <label for="title">Task Title:</label><br>
        <input type="text" id="title" name="title" required><br><br>
        
        <label for="description">Description:</label><br>
        <textarea id="description" name="description" rows="4" cols="50" required></textarea><br><br>
        
        <label for="dueDate">Due Date:</label><br>
        <input type="date" id="dueDate" name="dueDate" required><br><br>
        
        <label for="priority">Priority Level:</label><br>
        <select id="priority" name="priority" required>
            <option value="High">High</option>
            <option value="Medium">Medium</option>
            <option value="Low">Low</option>
        </select><br><br>

        <input type="hidden" name="page" value="createTask">
        <input type="submit" value="Update Task">
    </form>

    <!-- Button link for My Tasks page -->
    <a href="myTasks.jsp" class="my-tasks-link">Go to My Tasks</a>
</body>
</html>

