<%@page import="com.contacts.demo.beans.ContactBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="ISO-8859-1">
<title>My Contacts</title>
</head>

<!-- Navigation bar -->
<%@include file="navigation_bar.html"%>
<!-- Main content -->

<div class="content">
	<table class="list">
		<tr>
			<th>Name</th>
			<th>Mail</th>
			<th>Number</th>
		</tr>
		<c:forEach items="${contacts}" var="contact">
			<tr>
				<td class="to-round-first">${contact.getName()}${contact.getSurname()}</td>
				<td>${contact.getEmail()}</td>
				<td>${contact.getPhoneNum()}</td>
				<td class="to-round-last">
					<div>
						<div class="table-button">
							<form action="/edit_contact" method="GET">
								<input type="hidden" name="to_edit" value="${contact.getId()}">
								<button type="submit">
									<i class="fa fa-edit"></i>
								</button>
							</form>
						</div>
						<div class="table-button">
							<form action="delete_contact" method="GET">
								<input type="hidden" name="to_edit" value="${contact.getId()}">
								<button type="submit">
									<i class="fa fa-trash"></i>
								</button>
							</form>
						</div>
						<div class="table-button">
							<form action="/star_contact" method="GET">
								<input type="hidden" name="to_edit" value="${contact.getId()}">
								<button type="submit">
									<i class="fa fa-star"></i>
								</button>
							</form>
						</div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<body>
</body>
</html>