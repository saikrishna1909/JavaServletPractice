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

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
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

	String emailId = request.getParameter("emailId");
	


	try {
		
		 statement = connection.createStatement();
		 int rs=statement.executeUpdate("delete from user where email ='"+emailId+"'");
		PrintWriter out=response.getWriter();
		if(rs>0) {
			out.println("<h1>User Deleted Successfully</h1>");
		}
		else {
			out.println("<h1>User Deleting Unsuccessfully</h1>");

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
