<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.add_form{
	text-align: center;
	width: auto;
}
</style>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="add_form">
	<sf:form modelAttribute="contact" method="POST">
	<p>Name<br> <sf:input path="name"/>
	<p>Surname<br> <sf:input  path="surname"/>
	<p>Email<br> <sf:input path="email"/>
	<p>Name<br> <sf:input path="phoneNum"/><br>
	<div class="buttons_layout">
	<button type="submit">Add</button>
	<button type="reset">Reset</button>
	</div>
	</sf:form>
	<br>
	</form>
</div>
</body>
</html>