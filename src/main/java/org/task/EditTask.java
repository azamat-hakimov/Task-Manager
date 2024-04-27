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

@WebServlet("/edit")
public class EditTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String taskTitle = request.getParameter("title");
		String taskDescription = request.getParameter("description");
		String taskDueDate = request.getParameter("dueDate");
		String taskPriority = request.getParameter("priority");
		
		HttpSession session = request.getSession();
		int user_id = (int)session.getAttribute("user_id");
		
		
		String updateQuery = "UPDATE tasks SET description = ?, due_date = ?, priority = ? WHERE title = ? AND user_id = ?";
		try (Connection connection = getConnection();
				PreparedStatement updateStatement = connection.prepareStatement(updateQuery)){
			
			updateStatement.setString(1, taskDescription);
			updateStatement.setString(2, taskDueDate);
			updateStatement.setString(3, taskPriority);
			updateStatement.setString(4, taskTitle);
			updateStatement.setInt(5, user_id);
			
			
			int result = updateStatement.executeUpdate();
			if (result > 0) {
				response.sendRedirect(request.getContextPath() + "/myTasks.jsp");
			}else {
				response.sendRedirect(request.getContextPath() + "/error.jsp");
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
