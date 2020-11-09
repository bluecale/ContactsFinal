<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%@include file="layout.html" %>

<body>
	<div class="to_add" style="background-color:tomato">
		<c:forEach items="${sessionScope.toAdd}" var="contact" >
			<form action="/add_contact" method="GET">
				<p>${contact.getName()}</p>
				<br> <input type="hidden" value="${contact.id}" name="id">
				<input type="submit">
			</form>
		</c:forEach>
	</div>
	
	<div class="added" style="background-color:blue">
		<c:forEach items="${sessionScope.added}" var="contact" >
			<form action="remove_contact" method="GET">
				<p>${contact.getName()}</p>
				<br> <input type="hidden" value="${contact.id}" name="id">
				<input type="submit">
			</form>
		</c:forEach>
	</div>
	
	<form action="/create_group" method="post">
	Group Name:<input type="text" name="groupName">
	<input type="submit">
	
	</form>
	
	<form action="/groups_list">
	<input type="submit">
	</form>
	
	
	

</body>
</html>