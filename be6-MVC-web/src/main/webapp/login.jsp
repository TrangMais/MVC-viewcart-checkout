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
	<h1>Login Page</h1>
		<%
		String error = request.getParameter("error");

		if (error != null) {
			out.println(error);
		}
		%>
		
	<form action="LoginController" method="post">
	
		<div class="container">
			<label for="username"><b>Username</b></label> <input type="text"
				placeholder="Enter Username" name="username" required> <br>

			<label for="password"><b>Password</b></label> <input type="password"
				placeholder="Enter Password" name="password" required> <br>

			<button type="submit">Login</button>

		</div>
	</form>

	</div>

</body>
</html>