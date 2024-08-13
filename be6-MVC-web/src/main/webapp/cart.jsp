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
	<jsp:include page="header.jsp" />

	<div style="display: flex; padding: 20">
		<div style="width: 30%"></div>
		<div style="width: 70%">
			<c:if test="${not empty cart}">
				<h3>Products in Cart</h3>
				<hr>
				<c:forEach var="product" items="${cart}" varStatus="status">
					${status.count}: 
					<div class="product-name">${product.value.name}</div>
					<div class="product-price">Price: ${product.value.price}</div>
					<div class="product-quantity">Quantity:</div>
					<div class="product-price" id="total">Total: VND</div>
					<a href="#">Update</a> ---
					<a href="Cart?command=DELETE&productId=${product.key}">Delete</a>
					<br>
				</c:forEach>
				<br>
				<form action="Home">
					<input type="submit" value="Continue Shopping" />
				</form>
				<br>
				<input type="button" value="CHECKOUT" />
			</c:if>

			<c:if test="${empty cart}">
				<h3>Empty Cart</h3>
			</c:if>
		</div>

	</div>
</body>
</html>