<%@ page import = "java.sql.*" %>

<%
String username = request.getParameter("username");
String password = request.getParameter("password");
String gender = request.getParameter("gender");
String hobbies = request.getParameter("hobbies");
String languages = request.getParameter("languages");
String city = request.getParameter("city");

try {

	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/be6_web", "root", "root");
	PreparedStatement ps = conn.prepareStatement("insert into users(username, password, gender, hobbies, languages, cities) values(?, ?, ?, ?, ?, ?)");
	ps.setString(1, username);
	ps.setString(2, password);
	ps.setString(3, gender);
	ps.setString(4, hobbies);
	ps.setString(5, languages);
	ps.setString(6, city);
	int x = ps.executeUpdate();
	
	if(x > 0){
		out.println("Registration done sucessfully");
	}else{
		out.println("Registration failed");
	}
	
}

catch (Exception e) {
	out.println(e);
}
%>