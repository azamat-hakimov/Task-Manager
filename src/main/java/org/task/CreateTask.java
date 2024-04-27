package org.task;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/create")
public class CreateTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String taskTitle = request.getParameter("title");
		String taskDescription = request.getParameter("description");
		String taskDueDate = request.getParameter("dueDate");
		String taskPriority = request.getParameter("priority");
		
		HttpSession session = request.getSession();
		int user_id = (int)session.getAttribute("user_id");
		
		
		String insertQuery = "INSERT INTO tasks (user_id, title, description, due_date, priority) VALUES (?,?,?,?,?)";
		try (Connection connection = getConnection();
				PreparedStatement insertStatement = connection.prepareStatement(insertQuery)){
			
			insertStatement.setInt(1, user_id);
			insertStatement.setString(2, taskTitle);
			insertStatement.setString(3, taskDescription);
			insertStatement.setString(4, taskDueDate);
			insertStatement.setString(5, taskPriority);
			
			
			int result = insertStatement.executeUpdate();
			if (result > 0) {
				response.sendRedirect(request.getContextPath() + "/myTasks.jsp");
			}else {
				System.out.println("There is issue with insering data!!!");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
	
}
