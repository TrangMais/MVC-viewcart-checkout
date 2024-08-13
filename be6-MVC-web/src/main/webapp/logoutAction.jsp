<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.IOException" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
    // Invalidate the session and redirect to the login page
    //session.invalidate();

	session.removeAttribute("loggedInUser");
    response.sendRedirect("index.jsp");
    
    //Difference between session.invalidate and session.removeAttribute
    // Using the remove Attribute tag will just remove the stated attributes from the session. 
    // Using session invalidate will terminate the entire session.
%>
</body>
</html>