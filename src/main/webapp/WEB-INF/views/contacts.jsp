<%@page import="com.contacts.demo.beans.ContactBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">

</style>
<title>My Contacts</title>
</head>

<c:out value="${contacts}"></c:out>

<body>
</body>
</html>