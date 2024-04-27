package org.task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mytasks")
public class DisplayTasks extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if (session == null) {
            // Redirect to login if session is not available
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // Redirect to login if username is not available in session
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        int user_id = getUser_id(username);
        if (user_id == -1) {
            // Handle case where user is not found
            // You may want to redirect to an error page or display an error message
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }
        
        List<Task> tasks = getTasksForUser(user_id);
        
        // Set tasks in session
        session.setAttribute("user_id", user_id);
        session.setAttribute("tasks", tasks);
        
        // Redirect to the tasks page
        response.sendRedirect(request.getContextPath() + "/myTasks.jsp");
    }



    public static Connection getConnection() {
        Connection con = null;
        try {
            String dbUrl = "jdbc:mysql://localhost:3306/task-db";
            String username = "root"; 
            String password = "2005614";
            con = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException ex) {
        	
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return con;
    }
    
    protected int getUser_id(String username) {
        String readQuery = "SELECT user_id FROM users WHERE username = ?";
        int user_id = -1;
        try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/task-db", "root",
                "2005614");
                PreparedStatement readStatement = connect.prepareStatement(readQuery)) {
            readStatement.setString(1, username);
            ResultSet resultSet = readStatement.executeQuery();
            if (resultSet.next()) {
                user_id = resultSet.getInt("user_id");
                return user_id;

            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_id;
    }
    
    protected List<Task> getTasksForUser(int user_id) {
        List<Task> tasks = new ArrayList<>();
        String readTaskQuery = "SELECT * FROM tasks WHERE user_id = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement readTask = connection.prepareStatement(readTaskQuery)) {
            readTask.setInt(1, user_id);
            ResultSet resultOfTask = readTask.executeQuery();
            
            while (resultOfTask.next()) {
                String title = resultOfTask.getString("title");
                String description = resultOfTask.getString("description");
                String dueDate = resultOfTask.getString("due_date");
                String priority = resultOfTask.getString("priority");
                Task task = new Task(title, description, dueDate, priority);
                tasks.add(task);
            }
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
        }
        return tasks;
    }

}
