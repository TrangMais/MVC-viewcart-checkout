package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.User;

/**
 * Servlet implementation class LoginController
 */

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// catch - compile/ build time exception
		// uncatch - run time exception
		// int number = Integer.parseInt("abc");
		
		// CONFIG connection to MYSQL (LOCAL)??
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			//Because a lot of Servlet use this connection, we put these codes in a DBUtil java file
			//Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/be6_web", "root", "root");

			Connection conn = DBUtil.getConnection();

			String query = "SELECT * FROM `users` WHERE `username` = ? AND `password` = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			boolean isUserExist = rs.next();

			if (isUserExist) {
				User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("gender"),
									rs.getString("hobbies"), rs.getString("languages"), rs.getString("city"));
				// session = request.getSession(); This function is available only on JSP. With Servlet, we have to use HttpSession
				HttpSession session = request.getSession();
				session.setAttribute("loggedInUser", user);

				response.sendRedirect("Home");

			} else {
				response.sendRedirect("login.jsp?error=username or password incorrect");
			}
		}catch (Exception e) {
		// Exception is everything including catch and uncatch
		//catch (SQLException | NumberFormatException e) {
		//System.out.println("Error happened " + e.getMessage());
			System.out.println("LoginController doPost Eror happened " + e.getMessage());
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
	}

}
