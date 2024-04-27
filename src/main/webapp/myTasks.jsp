<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.task.Task" %>
<%@ page import="org.task.DisplayTasks" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Get Organized! - TaskMaster</title>
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
        .task-container {
            width: 80%;
            margin: 0 auto;
            text-align: left;
        }
        .task-item {
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            padding: 20px;
            margin-bottom: 20px;
        }
        .task-item strong {
            font-weight: bold;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }
        .task-button-container {
            display: flex;
            gap: 10px;
            align-items: center;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        button:hover {
            background-color: #45a049;
        }
        .edit-button {
            background-color: #007bff;
        }
        .delete-button {
            background-color: #dc3545;
        }
        .refresh-button {
            background-color: #17a2b8;
        }
        .create-button {
            background-color: #28a745;
        }
        .back-button {
            background-color: #333; /* Darker background color */
        }
        .back-button:hover {
            background-color: #555; /* Darker background color on hover */
        }
    </style>
</head>
<body>
    <header>
        <h1>Get Organized! - TaskMaster</h1>
        <button onclick="location.href='login.jsp'" class="back-button">Log Out</button>
    </header>
    <div class="task-container">
        <% List<Task> tasks = (List<Task>) session.getAttribute("tasks");
        if (tasks != null && !tasks.isEmpty()) {
            for (Task task : tasks) { %>
            <div class="task-item">
                <strong>Title:</strong> <%= task.getTitle() %><br>
                <strong>Description:</strong> <%= task.getDescription() %><br>
                <strong>Due Date:</strong> <%= task.getDueDate() %><br>
                <strong>Priority:</strong> <%= task.getPriority() %><br>
            </div>
        <% }
        } else { %>
        <p>You don't have any tasks yet.</p>
        <% } %>
    </div>
    <div class="button-container">
        <div class="task-button-container">
            <button onclick="location.href='editTask.jsp'" class="edit-button">Edit Task</button>
            <button onclick="location.href='deleteTask.jsp'" class="delete-button">Delete Task</button>
        </div>
        <form action="<%= request.getContextPath() %>/mytasks" method="get">
            <button type="submit" class="refresh-button">Refresh</button>
        </form>
        <button onclick="location.href='createTask.jsp'" class="create-button">Create New Task</button>
    </div>
    <p style="color: #777; font-style: italic; margin-bottom: 10px;">Please refresh the page after login/Sign Up or any changes made!</p>
</body>
</html>