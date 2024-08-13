<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- Fifth requirement: How to use repeatedly the code above for every page -->
	<%--=
	<% if (session.getAttribute("loggedInUser") != null) {%>
	<p>HELLO ${sessionScope.loggedInUser.username},</p>
	<a href="logout.jsp">LOGOUT</a>
	<%} else {%>
	<a href="login.jsp">LOGIN</a>
	<%}%>
	--%>
	
	<!-- Improved code without IF OR ELSE from java code. So that JSP - Standard Tag Library have c:if -->
	<c:if test="${not empty sessionScope.loggedInUser}">
		<p>HELLO ${sessionScope.loggedInUser.username},</p>
		<a href="logoutAction.jsp"> Logout</a>
	</c:if>
	<c:if test="${empty sessionScope.loggedInUser}">
		<a href="login.jsp"> Login</a>
	</c:if>	
	
	<!-- IN THE HEADER AREA -> next to LOGIN link -> "CART(0)" link -->
	<hr>
	<!-- 1st step: create the view to imagine the flow of code -->
	<%--=
	<h3>Shopping Cart (0)</h3>
	--%>
	
	<!-- 2nd step: after create the CartController, change the link -->
	<%--=
	<h3>Shopping Cart (${sessionScope.cart.size()})</h3>
	--%>
	
	<!-- 3rd step: CART() -> CART(0) -->
	<%--=
	<h3>
	<c:if test="${empty sessionScope.cart.size()}">
		Shopping Cart (${sessionScope.cart.size()}0)
	</c:if>
	<c:if test="${not empty sessionScope.cart.size()}">
		Shopping Cart (${sessionScope.cart.size()})
	</c:if>
	</h3>
	--%>
	<a href="Cart?command=VIEW_CART">
		Shopping Cart(${empty sessionScope.cart? 0 : sessionScope.cart.size()})</a>
</body>
</html>