<%@page import="com.contacts.demo.beans.ContactBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<style type="text/css">
@charset "ISO-8859-1";

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #247ba0;
	height: 10%;
}

.topnav a {
	float: left;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 20px;
}

.topnav a:hover {
	background-color: #cbf3f0;
	color: black;
	border-radius: 100px;
}

.topnav a.active {
	background-color: #4CAF50;
	color: white;
}

.content {
	padding-top: 25px;
	text-align: center;
	height: 45em;
	background-color: #f8edeb;
}

.form-wrapper {
	text-align: center;
	background: dodgerblue;
	position: absolute;
	top: 25%;
	left: 30%;
	right: 30%;
	background-color: #cbf3f0;
	border-radius: 10px;
}

.contact-form {
	padding-top: 2%;
	padding-bottom: 5%;
}

.button-layout {
	padding-top: 10px;
	color: tomato;
}

.form-button {
	font-size: 20px;
	margin: 5px;
	width: 50px;
	background-color: white;
}

.form-button:hover {
	background-color: grey;
}

.list {
	width: 80%;
}

.list tr:hover {
	background-color: blue;
}

input {
	border-radius: 15px;
	padding: 7px;
	padding-left: 15px;
	border-style: hidden;
}

input:focus {
	outline: 0px;
}

.form-button {
	border-radius: 5px;
	border-style: hidden;
	margin: 15px;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #247ba0;
	height: 10%;
}

.topnav a {
	float: left;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 20px;
}

.topnav a:hover {
	background-color: #cbf3f0;
	color: black;
	border-radius: 100px;
}

.topnav a.active {
	background-color: #4CAF50;
	color: white;
}

.content {
	padding-top: 25px;
	text-align: center;
	height: 45em;
	background-color: #f8edeb;
}

.form-wrapper {
	text-align: center;
	background: dodgerblue;
	position: absolute;
	top: 25%;
	left: 30%;
	right: 30%;
	background-color: #cbf3f0;
	border-radius: 10px;
}

.contact-form {
	padding-top: 2%;
	padding-bottom: 5%;
}

.button-layout {
	padding-top: 10px;
	color: tomato;
}

.form-button {
	font-size: 20px;
	margin: 5px;
	width: 50px;
	background-color: white;
}

.form-button:hover {
	background-color: grey;
}

.list {
	width: 80%;
}

.list tr:hover {
	background-color: blue;
}

input {
	border-radius: 15px;
	padding: 7px;
	padding-left: 15px;
	border-style: hidden;
}

input:focus {
	outline: 0px;
}

.form-button {
	border-radius: 5px;
	border-style: hidden;
	margin: 15px;
}
</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="ISO-8859-1">
<title>My Contacts</title>
</head>

<!-- Navigation bar -->
<div class="topnav">
	<a href="/add"><i class="fa fa-user-plus"></i> Add contact</a> <a
		href="/contacts"><i class="fa fa-users"></i> Contats</a> <a
		href="/groups_list"><i class="fa fa-users"></i> Contats</a> <a
		href="/new_group"><i class="fa fa-users"></i> Contats</a>
</div>

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
			<td> ${contact.getName()} ${contact.getSurname()}</td>
			<td> ${contact.getEmail()}
			<td> ${contact.getPhoneNum()} </td>
		</tr>
		</c:forEach>
	</table>
</div>
<body>
</body>
</html>