<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width">
<title>Insert title here</title>

</head>

<!-- Navigation bar -->
<%@include file="navigation_bar.html"%>
<!-- Main content -->
<body style="user-select: none;">

	<div class="content">
		<form action="/search_groups" method="GET">
			<input type="text" name="search" class="search-bar"
				placeholder="Search...">
			<button type="submit" class="search-button">
				<i class="fa fa-search"></i>
			</button>
		</form>
		<div class="list-wrap">
			<table class="group_table center">
				<c:forEach items="${groups.keySet()}" var="key">
					<tr class="breakrow">
						<th><span>+</span> ${key}</th>
					</tr>
					<c:forEach items="${groups.get(key)}" var="contact">
						<tr class="just-contacts" style="display: none;">
							<td class="to-round-all">${contact.getName()}
								${contact.getSurname()}</td>
						</tr>
					</c:forEach>
				</c:forEach>
			</table>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$('.breakrow').click(function() {
				$(this).nextUntil('tr.breakrow').slideToggle(50);
				$(this).find('span').text(function(_, value) {
					return value == '-' ? '+' : '-'
				});
			});
		});
	</script>

</body>
</html>