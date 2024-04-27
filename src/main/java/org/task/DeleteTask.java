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

@WebServlet("/delete")
public class DeleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String taskTitle = request.getParameter("taskName").toLowerCase();
		
		
		String readQuery = "DELETE FROM tasks WHERE title = ?";
		try (Connection connection = getConnection();
				PreparedStatement deleteStatement = connection.prepareStatement(readQuery)){
			
			deleteStatement.setString(1, taskTitle);
			
			int result = deleteStatement.executeUpdate();
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
	        System.out.println("Connected to the database successfully.");
	    } catch (SQLException ex) {
	        System.out.println("Failed to connect to the database.");
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
	    }
	    return con;
	}

		
}

