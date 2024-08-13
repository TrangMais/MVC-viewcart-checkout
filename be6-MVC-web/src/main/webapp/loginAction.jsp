<%@page import="entity.User"%>
<%@ page import="java.sql.*"%>

<%@ page import = "java.util.*"%>

<%
String username = request.getParameter("username");
String password = request.getParameter("password");

try {
	// Config connection to MySQL (Local)
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/be6_web", "root", "root");
	
	String query = "SELECT * FROM `users` WHERE `username` = ? AND `password` = ?";
	PreparedStatement ps = conn.prepareStatement(query);
	ps.setString(1, username);
	ps.setString(2, password);
	ResultSet rs = ps.executeQuery();
	
	//String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
	//Statement statement = conn.createStatement();
	//ResultSet rs = statement.executeQuery(query);
	
	boolean isUserExist = rs.next();
	// re.next(): result set point (đang trỏ tới dòng đầu tiên) to the first line
	// isUserExist = true when rs has 1 row
	// isUserExist = false when rs has 0 row
	
	if(isUserExist) {
	// First requirement: If log in sucessfully, redirect to HOMEPAGE (index.html)
	// response.sendRedirect("index.html");
	
	// Second requirement: After log in, redirect to index.html with the message "Hello the user's name"
	
	// When the RS point to the first line and it has data because of LOGIN successfully, RS read every columnn's value
	// User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("gender"),
				//rs.getString("hobbies"), rs.getString("languages"), rs.getString("city"));
	
	// Then, redirect to HOMEPAGE and have the USER'S NAME on the HOMEPAGE
    // response.sendRedirect("index.jsp?username=" + user.getUsername());
	
	// Third requirement: NO Login AGAIN if user can login successfully EVEN THOUGH cloding browser
	// the code before does not meet the requirement
	
	// If the user LOGIN successfully, RS will take the object "User and its information" out
		User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("gender"),
				rs.getString("hobbies"), rs.getString("languages"), rs.getString("city"));
		session = request.getSession(); 
	// from request of client, the server will get session (căn cước công dân),
	// then server can access the box of information
	// (When user login successfully, the server will take the information User user and) put inside the box
		session.setAttribute("loggedInUser", user);
		response.sendRedirect("index.jsp");
		
	}else{
		response.sendRedirect("login.jsp?error=username or password incorrect");
		//Add more parameter on the page, we use "?"
	}
}
	
catch (Exception e) {
	out.println(e);
}
%>