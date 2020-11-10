<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="layout.html"%>

<table>
<c:forEach items="${groups.keySet()}" var="key">
<tr>
<th><b>${key}</b></th>
<c:forEach items="${groups.get(key)}" var="contact">
<tr>
<td>${contact.getName()} ${contact.getSurname()} </td>
</c:forEach>
</c:forEach>

</table>
<!--

----------------------------

${ids} -->

</body>
</html>