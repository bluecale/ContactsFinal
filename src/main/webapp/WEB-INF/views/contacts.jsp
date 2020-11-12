<%@page import="com.contacts.demo.beans.ContactBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="ISO-8859-1">
<style type="text/css">

.contacts-nav-button{
	background-color: #cbf3f0;
	color: black;
}
</style>
<title>My Contacts</title>
</head>

<!-- Navigation bar -->
<%@include file="navigation_bar.html"%>
<!-- Main content -->


<div class="content">
	<div>
		<form action="/search_contacts" method="GET">
			<input type="text" name="search" class="search-bar"
				placeholder="Search...">
			<button type="submit" class="search-button">
				<i class="fa fa-search"></i>
			</button>
		</form>
	</div>

	<div class="list-wrap">
		<table class="list">
			<tr class="list-head">
				<th>Name</th>
				<th>Mail</th>
				<th>Number</th>
				<th></th>
			</tr>
			<tr>
				<td>Contacts (${contacts.size()})</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach items="${contacts}" var="contact">
				<tr class="just-contacts">
					<td class="to-round-first">${contact.getName()}
						${contact.getSurname()}</td>
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
								<form action="delete" method="GET">
									<input type="hidden" name="to_edit" value="${contact.getId()}">
									<button type="submit">
										<i class="fa fa-trash"></i>
									</button>
								</form>
							</div>
							<div class="table-button">
								<form action="/starred" method="GET">
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
			<tr>
				<td>Starred (${starred.size()})</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach items="${starred}" var="contact">
				<tr class="just-contacts">
					<td class="to-round-first">${contact.getName()}
						${contact.getSurname()}</td>
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
								<form action="delete" method="GET">
									<input type="hidden" name="to_edit" value="${contact.getId()}">
									<button type="submit">
										<i class="fa fa-trash"></i>
									</button>
								</form>
							</div>
							<div class="table-button">
								<form action="/starred" method="GET">
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
</div>
<%@include file="footer.html"%>
<body>
</body>
</html>