<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>User Info</title>
</head>
<nav class="navbar navbar-expand-lg sticky-top bg-secondary">
<form class="form-inline mr-2 my-lg-0">
  	   <div class="col-auto">
      <button type="button" id="newUser" class="btn btn-primary mr-2 my-sm-0">New</button>
    </div>
    <input class="form-control my-2 my-sm-0" id="myInput" type="search" placeholder="Search..">
  	</form>
</nav>
	<table class="table table-striped" id="userTable">
		<thead>
			<tr>
				<th><img src="${pageContext.request.contextPath}/gearIcon.png" /></th> 
				<th>User ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Phone number</th>
				<th> </th>
			</tr>
		</thead>
		<c:forEach items="${users}" var="usersList">
			<tr>
			<td>
				<div class="form-check">
				<input class="form-check-input" type="checkbox" value="" id="tableCheckBox"></div>
					                            </td> 
			    <td class="userName">${usersList.userName}</td>
				<td class="firstName">${usersList.firstName}</td>
				<td class="lastName">${usersList.lastName}</td>
				<td class="user_name">${usersList.userName}</td>
				<td class="phone_number">${usersList.phone_number}</td>
				<td class="trash" onclick="deleteUser('${usersList.userName}')"><img src="${pageContext.request.contextPath}/trash.png" /></td>
				</tr>
		</c:forEach>
	</table>
<script>
document.getElementById("newUser").addEventListener("click", myFunction);

function myFunction() {
 window.location.replace('newUser');

}
		$(document).ready(function() {
			$("#myInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#userTable tr:not(:first)").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
		});
		
		$('input[type="checkbox"]').click(function () {
		});
		if (screen.width < 500) {
			  
			  $("body").addClass("nohover");
			  $("td, th")
			    .attr("tabindex", "1")
			    .on("touchstart", function() {
			      $(this).focus();
			    });
			  }
		
	 	function deleteUser(userName){
	 		
	 		$.ajax({
	 			  url:"userInfo",
	 			  type:"POST",
	 			  data:{
	 				 "userName" : userName,
	 					},	
	 			  success: function(response){
	 			  	
	 			    debugger;
	 			  },
	 			  error: function(error){
	 			  	debugger;
	 			  }
	 			});
			} 
	</script>