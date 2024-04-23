package com.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId= request.getParameter("userId");
		String password = request.getParameter("password");
		
		//JDBC
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wtloginservlet","root","Eqpo0218@anu");
				Statement st = conn.createStatement();
				String query ="select * from users where userid='"+userId+"' and password='"+password+"'";
				ResultSet rs = st.executeQuery(query);
				if(rs.next()) {
					out.print("<h1>"+userId+":Welcome to Login Page</h1><br>");
					out.print("<h1>Login Successful</h1><br>");
					out.print("UserId:");
					out.print(userId);
					out.print("Password: ");
					out.print(password);
				}
				else {
					out.print("<h1>"+userId+"Enter correct UserId</h1><br>");
					
				}
				rs.close();
				st.close();
				conn.close();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
					out.print("Server cannot connect");
					e.printStackTrace();
				
			}
			
		} catch (ClassNotFoundException e) {
			out.print("<h1>Login Failed Server</h1><br>");
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
