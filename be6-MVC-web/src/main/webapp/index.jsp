<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<h1><strong>HOME PAGE</strong></h1>
	<hr>
		<!-- First requirement: to link to the page Product
			<a href="http://localhost:8080/be6-web/products.jsp">GO TO THE PRODUCT PAGE</a> -->

		<!-- Second requirement: After log in, redirect to index.html with the link of login 
			<a href="http://localhost:8080/be6-web/login.jsp">LOGIN</a>  -->

		<!-- Third requirement: After log in, redirect to index.html with the message "Hello the user's name"
			If log in successfully, the HOMEPAGE have ONLY Option LOGOUT
			If the user try to come to LOGIN again, they cannot redirect -->
		<%--= 
			<% 
			if (request.getParameter("username") != null){
				%>
				<p> Hello ${param.username}, Welcome to HOMEPAGE </p>
				<br> <a href="logout.jsp">LOGOUT</a>
			<%}
			else{%>
				<a href="login.jsp">LOGIN</a>
			<%}%>
			--%>

		<!-- Forth requirement: After log in, close the browser and re-open browser ASKs for logging in AGAIN
			How to PREVENT this situation happened. NO Login AGAIN if user can login successfully 
			= REQUEST with the same login, have to keep the connection from the user -->

		<!-- When we input any object "User" into Session, every JSP file can take the object from directly Session
			WITHOUT	through parameter. Because Session is always on Server -->
		<%--= 
			<% User user = (User) session.getAttribute("loggedInUser");
			if (user != null){
				%>
				<%--= <p> Hello ${user.username}, Welcome to HOMEPAGE </p> --%>
		<!--the point between user and username = user.getUsername(). 
					But if we have the User.java. We can do like the following code line -->
		<%--= <p> Hello ${sessionScope.loggedInUser.username}, Welcome to HOMEPAGE </p>
				<a href="logout.jsp">LOGOUT</a>
			<%}else{%>
				<a href="login.jsp">LOGIN</a>
			<%}%> 
			--%>
		<!-- Improved code without using USER -->
		<%--= 
			<% 
			if (session.getAttribute("loggedInUser") != null){%>
			<p> Hello ${sessionScope.loggedInUser.username}, Welcome to HOMEPAGE </p>
				<a href="logout.jsp">LOGOUT</a>
			<%}else{%>
				<a href="login.jsp">LOGIN</a>
			<%}%> 
			--%>

		<!-- Improved code without IF OR ELSE from java code. So that JSP - Standard Tag Library have c:if 
			see on header.jsp
			Muốn có cái thông tin loggedInUser thì phải lấy trong session. 
			Mà trước hết phải bỏ thông tin logggedInUser vào ngăn tủ của session lúc user đăng nhập
			Sau đó mới có thông tin để lấy -->
		<%--= 
			<c:if test="${not empty sessionScope.loggedInUser}">
				<p>HELLO ${sessionScope.loggedInUser.username},</p>
				<a href="logoutAction.jsp"> Logout</a>
			</c:if>
			<c:if test="${empty sessionScope.loggedInUser}">
				<a href="login.jsp"> Login</a>
			</c:if> 
		--%>

		<!-- Fifth requirement: How to use repeatedly the code above for every page -->
		<!-- HEADER AREA -->
		<%@include file='header.jsp'%>
		<hr>
		<!-- ending HEADER AREA -->
		
		<!-- SEVENTH requirement: SHOW CATEGORY in DATABASE FIRSTLY -->
		<!-- hardcore firstly for seeing the view -->
		<%--=
		Categories:
		<a href="#">Drink</a>
		<a href="#">Food</a>
		<a href="#">Fruits</a>
		--%>
		<p><strong>Categories:</strong></p>
		<c:forEach var="category" items="${categoryList}">
				<a href="Home?categoryId=${category.id}">${category.name}</a>
		</c:forEach>
		
		<!-- # means click this name, nothing redirect to any link -->
		<hr>
		
		<!-- Sixth requirement: SHOW ALL PRODUCTS in DATABASE -->
		<!-- c:forEach is the syntax for loop
			is similar with for each. Values in "items" have to be Array. 
			"var" is each product in "items" -->
		<!-- PRODUCT AREA -->
		<p><strong>Products:</strong></p>
		<c:forEach var="product" items="${productList}">
				${product.name} - 
		<!-- Click view "More" on HOME PAGE (More link after each product) -->	
				<a href="Product?id=${product.id}">More</a><br>
		<!--Requirement: After click the button "More", SHOW product details page -> show name, price , description of product
			We need to make a MVC for Product: Controller handle request and send to View: product.jsp, ProductController, ProductRepository -->
		</c:forEach>

		<!--productList is stored in session. 
			But if we want to take the productList from session, 
			we need to have a place for this in session -->
		<!-- ending PRODUCT AREA -->

	</div>

</body>
</html>