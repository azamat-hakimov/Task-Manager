package org.task;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/authenticate")
public class UserAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String database_URL = "jdbc:mysql://localhost:3306/task-db";
		String username_db ="root";
		String password_db = "2005614";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String page = request.getParameter("page").toLowerCase();
		
		switch (page) {
		case "signup":
			handleSignup(request,response,database_URL,username_db,password_db);
			break;
		case "login":
			handleLogin(request,response,database_URL,username_db,password_db);
		}
	}

	
	protected void handleSignup(HttpServletRequest request, HttpServletResponse response,
	        String URL, String username, String password) throws ServletException, IOException {

	    String readQuery = "SELECT * FROM users WHERE username = ?";
	    String insertQuery = "INSERT INTO users (username, password) VALUES  (?,?)";

	    String inputUsername = request.getParameter("username");
	    String inputPassword = request.getParameter("password");
	    String inputPasswordConfirm = request.getParameter("confirm_password");

	    try (Connection connect = DriverManager.getConnection(URL, username, password);
	         PreparedStatement statement = connect.prepareStatement(readQuery)) {

	        if (inputPassword.equals(inputPasswordConfirm)) {
	            statement.setString(1, inputUsername);

	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                request.getRequestDispatcher("/error.jsp").forward(request, response);
	            } else {
	                try (PreparedStatement insertStatement = connect.prepareStatement(insertQuery)) {

	                    insertStatement.setString(1, inputUsername);
	                    insertStatement.setString(2, inputPassword);

	                    int result = insertStatement.executeUpdate();
	                    if (result > 0) {

	                        HttpSession session = request.getSession();
	                        session.setAttribute("username", inputUsername);

	                        request.getRequestDispatcher("/myTasks.jsp").forward(request, response);
	                    } else {
	                    	request.getRequestDispatcher("/error.jsp").forward(request, response);
	                    }

	                }
	            }

	        } else {
	            // Passwords do not match
	            request.getRequestDispatcher("/error.jsp").forward(request, response);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	
	protected void handleLogin(HttpServletRequest request, HttpServletResponse response,
			String URL, String username, String password) 
			throws ServletException, IOException {
		
		String readQueary = "SELECT * FROM users WHERE username = ? AND password = ?";
		
		String inputUsername = request.getParameter("username");
		String inputPassword = request.getParameter("password");
		
		try (Connection connect = DriverManager.getConnection(URL,username,password);
				PreparedStatement statement = connect.prepareStatement(readQueary)){
			
			statement.setString(1, inputUsername);
			statement.setString(2, inputPassword);
			
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				String dbPassword = resultSet.getString("password");
				
				if (inputPassword.equals(dbPassword)) {
					
					HttpSession session = request.getSession();
					session.setAttribute("username", inputUsername);
					 
					session.setAttribute("loginTime", System.currentTimeMillis()); 
				  
				    session.setMaxInactiveInterval(24 * 60 * 60);
					
					request.getRequestDispatcher("/myTasks.jsp").forward(request, response);
				}
			}else {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
