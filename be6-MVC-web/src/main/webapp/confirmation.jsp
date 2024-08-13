<%@page import="util.ParamUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div>
<%
	String hbString = ParamUtil.convertArrayToString(request.getParameterValues("hobbies"));
	String languageString = ParamUtil.convertArrayToString(request.getParameterValues("languages"));

%>
	<h1>Confirmation before Submission</h1>
	<p><strong>Please review your information before submitting</strong></p>
	<form action="confirmationAction.jsp" method="post">
	
	<p><strong>Username:</strong> ${param.username}</p>
	<p><strong>Password:</strong> ${param.password}</p>
	<p><strong>Gender:</strong> ${param.gender} </p>
	
	<%-- because Hobby is an Array with many values so that we need to do to return all values 
		We should use java code--%>
	<%--= 
		String [] hobbies = request.getParamaterValues("Hobby");
		for (String hobby : hobbies){
			hbString += hobby + " ";
		}
		--%>
	<%-- we apply for language. However, to prevent the repeat for JAVA code.
		We create a ParamUtil.java--%>
		
	<p><strong>Hobbies:</strong> <%= hbString %></p>
	<p><strong>Languages:</strong> <%= languageString %></p>
	<p><strong>City:</strong> ${param.city}</p>
	
	<%--= to embed (nhúng) code from Java into jsp we can use this way for one line
	 		<%= request.getParameter("username")%> --%>
	
	<%-- for many line we use <% %> --%>
	
	<button>Confirm</button>
	
	<%-- A hidden field lets web developers include data that cannot be seen or modified 
		by users when a form is submitted. --%>
	<input name="username" value = "${param.username}" hidden=true>
	<input name="password" value = "${param.password}" hidden=true>
	<input name="gender" value = "${param.gender}" hidden=true>
	<input name="hobbies" value = "<%= hbString %>" hidden=true>
	<input name="languages" value = "<%= languageString %>" hidden=true>
	<input name="city" value = "${param.city}" hidden=true>
	</form>
	
	</div>
</body>
</html>