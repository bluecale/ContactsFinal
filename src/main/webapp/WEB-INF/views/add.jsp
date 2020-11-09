<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="navigation_bar.html"%>

	<div class="error_msg">${message}</div>

	<div class="content">
		<div class="form-wrapper">
			<div class="contact-form">
				<sf:form modelAttribute="contact" method="POST">
					<p>
						Name<br>
						<sf:input path="name" />
					<p>
						Surname<br>
						<sf:input path="surname" />
					<p>
						Email<br>
						<sf:input path="email" />
					<p>
						Number<br>
						<sf:input path="phoneNum" />
						<br>
					<div class="buttons_layout">
						<button type="submit">Add</button>
						<button type="reset">Reset</button>
					</div>
				</sf:form>
				<br>
			</div>
		</div>
	</div>
</body>
</html>