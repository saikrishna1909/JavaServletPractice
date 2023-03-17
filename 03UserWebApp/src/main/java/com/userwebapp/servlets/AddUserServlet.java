package com.userwebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;
	Statement statement = null;
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/my_db","root","SAIKrishna@1");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String firstName = request.getParameter("firstName");
	String lastName= request.getParameter("lastName");
	String emailId = request.getParameter("emailId");
	String password = request.getParameter("password");


	try {
		
		 statement = connection.createStatement();
		 int rs=statement.executeUpdate("insert into user values('"+firstName+"', '"+lastName+"', '"+emailId+"', '"+password+"')");
		PrintWriter out=response.getWriter();
		if(rs>0) {
			out.println("<h1>User Data Added Successfully</h1>");
		}
		else {
			out.println("<h1>User Data Adding Unsuccessfully</h1>");

		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
		statement.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
