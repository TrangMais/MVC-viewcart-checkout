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
<!-- Fifth requirement: How to use repeatedly the code above for every page -->
			<%@include file='header.jsp'%>
    <h2>Product Detail</h2>
    <c:if test="${not empty product}">
        <p>ID: ${product.id}</p>
        <p>Name: ${product.name}</p>
        <p>Description: ${product.description}</p>
        <p>Price: ${product.price}</p>
        <p>Category Id: ${product.category_id}</p>

<!-- add quantity box to select/changed when click "ADD TO CART" -->
	<h3>Quantity</h3>
	<input type="hidden" name="productId" value="${product.id}">
	<input type="number" name="quantity" value="1" width="8" style="width:20px">
	<br>
	
<!-- add button "ADD TO CART" to PRODUCT DETAILS PAGE -->
	<h3>Add</h3>
	<form action="Cart" method="post">
		<input type="hidden" name="productId" value="${product.id}">
		<input type="button" value="ADD TO CART"
			onclick="window.location.href='Cart?command=ADD_TO_CART&productId=${product.id}'"/>
		
	</form>
	<br>
	
        <!-- in the END of the page -> show a text box for customer input comment -->
   <h3>Leave Comment</h3>
        <form action="Comment" method="post">
            <input type="hidden" name="productId" value="${product.id}">
            <textarea name="content" rows="4" cols="50" placeholder="Please leave your comment here"></textarea><br>
            <input type="submit" value="Submit">
        </form>
        
        <!-- When customer submit form -> comment is added in db and reload the page with customers comment in the end of the page -->
   <h2>Comments</h2>
   		<c:forEach var="comment" items="${comments}">
   			${comment.content}<br>
   		</c:forEach>
        
    </c:if>
    <c:if test="${empty product}">
        <p>Product not found.</p>
        <a href="Home">Back to Homepage</a>
    </c:if>
</body>
</html>