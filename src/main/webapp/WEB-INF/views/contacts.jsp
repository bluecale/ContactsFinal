<%@page import="com.contacts.demo.beans.ContactBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="ISO-8859-1">
<style type="text/css">
</style>

<script type="text/javascript">
function doit(this_checkbox){
	var checkSameId = document.getElementsByName(this_checkbox.name);
	for (let item of checkSameId){
		item.checked = this_checkbox.checked;
	}
	var checkboxes = document.getElementsByClassName("check");
	var atLeastOneChecked = false;
	for(let item of checkboxes){
		if (item.checked){
			atLeastOneChecked = true;
			break;
		}
	}
	if (atLeastOneChecked){
		document.getElementById("name-table-tr").innerHTML = "";
		document.getElementById("mail-table-tr").innerHTML = "";
		document.getElementById("number-table-tr").innerHTML = "";
		document.getElementById("name-table-tr").innerHTML = document.getElementById("hello").innerHTML;
	} else {
		document.getElementById("name-table-tr").innerHTML = "Name";
		document.getElementById("mail-table-tr").innerHTML = "Mail";
		document.getElementById("number-table-tr").innerHTML = "Number";
	}
}
<c:if test="${editModalOpen == true}">
$(window).on('load',function(){
    $('#editModal').modal('show');
    $('#editModal').modal({backdrop: 'static', keyboard: false});  
});
</c:if>

<c:if test="${addModalOpen == true}">
$(window).on('load',function(){
    $('#addModal').modal('show');
    $('#addModal').modal({backdrop: 'static', keyboard: false});  
});
</c:if>

<c:if test="${groupModal == true}">
$(window).on('load',function(){
    $('#groupModal').modal('show');
    $('#groupModal').modal({backdrop: 'static', keyboard: false});  
});
</c:if>
</script>
<title>My Contacts</title>
</head>

<!-- Navigation bar -->
<%@include file="navigation_bar.html"%>
<!-- Main content -->

<form action="/new_group" method="POST" id="multiple_selection"></form>


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
				<th id="placeholder"></th>
				<th colspan="1" id="name-table-tr" style="">Name</th>
				<th id="mail-table-tr">Mail</th>
				<th id="number-table-tr">Number</th>
				<th></th>
			</tr>
			<tr>
				<td colspan="2"><h6 style="margin: 0px">STARRED
						(${starred.size()})</h6></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach items="${starred}" var="contact">
				<tr class="just-contacts">
					<td class="to-round-first"><input class="check"
						type="checkbox" form="multiple_selection" onclick="doit(this)"
						name="${contact.getId()}"></td>
					<td>${contact.getName()}&nbsp${contact.getSurname()}</td>
					<td>${contact.getEmail()}</td>
					<td>${contact.getPhoneNum()}</td>
					<td class="to-round-last">
						<div>
							<div class="table-button">
								<form action="/edit_contact" method="GET">
									<input type="hidden" name="to_edit" value="${contact.getId()}">
									<button type="submit">
										<i class="fa fa-edit fa-lg"></i>
									</button>
								</form>
							</div>
							<div class="table-button">
								<form action="delete" method="GET">
									<input type="hidden" name="to_edit" value="${contact.getId()}">
									<button type="submit">
										<i class="fa fa-trash fa-lg"></i>
									</button>
								</form>
							</div>
							<div class="table-button">
								<form action="/starred" method="GET">
									<input type="hidden" name="to_edit" value="${contact.getId()}">
									<button type="submit">
										<i class="fa fa-star fa-lg"></i>
									</button>
								</form>
							</div>
						</div>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2"><h6 style="margin: 0px">CONTACTS
						(${contacts.size()})</h6></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach items="${contacts}" var="contact">
				<tr class="just-contacts">
					<td class="to-round-first"><input class="check"
						type="checkbox" form="multiple_selection" onclick="doit(this)"
						name="${contact.getId()}"></td>
					<td>${contact.getName()}&nbsp${contact.getSurname()}</td>
					<td>${contact.getEmail()}</td>
					<td>${contact.getPhoneNum()}</td>
					<td class="to-round-last">
						<div>
							<div class="table-button">
								<form action="/edit_contact" method="GET">
									<input type="hidden" name="to_edit" value="${contact.getId()}">
									<button type="submit">
										<i class="fa fa-edit fa-lg"></i>
									</button>
								</form>
							</div>
							<div class="table-button">
								<form action="delete" method="GET">
									<input type="hidden" name="to_edit" value="${contact.getId()}">
									<button type="submit">
										<i class="fa fa-trash fa-lg"></i>
									</button>
								</form>
							</div>
							<div class="table-button">
								<form action="/starred" method="GET">
									<input type="hidden" name="to_edit" value="${contact.getId()}">
									<button type="submit">
										<i class="fa fa-star-o fa-lg"></i>
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
<div id="hello" style="visibility: hidden; max-height: 0px">
	<div class="multiple-button-first">
		<button type="submit" form="multiple_selection" class="mult-button" data-toggle="modal"
			data-target="#modelWindow" data-backdrop="static"
			data-keyboard="false">
			<i class="fa fa-users fa-lg"></i>
		</button>
	</div>
	<div class="multiple-button">
		<button type="submit" class="mult-button">
			<i class="fa fa-envelope fa-lg"></i>
		</button>
	</div>
	<div class="multiple-button">
		<button type="submit" class="mult-button">
			<i class="fa fa-ellipsis-v fa-lg"></i>
		</button>
	</div>
</div>


<%@include file="groupModal.html"%>
<%@include file="editModal.html"%>
<%@include file="addModal.html"%>
<%@include file="footer.html"%>
</body>
</html>