<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>Product List</title>
</head>
<nav class="navbar navbar-expand-lg sticky-top bg-secondary">
<form class="form-inline mr-2 my-lg-0">
  	   <div class="col-auto">
      <button type="button" id="newButton" class="btn btn-primary mr-2 my-sm-0">New</button>
    </div>
    <input class="form-control my-2 my-sm-0" id="myInput" type="search" placeholder="Search..">
  	</form>
</nav>
	<table class="table table-striped" id="myTable">
		<thead>
			<tr>
				<th><img src="${pageContext.request.contextPath}/gearIcon.png" /></th>
				<th><i class="fas fa-bars"></i> Asset ID  <div class="dropdown-menu"> </div></th>
				<th><i class="fas fa-bars"></i> Description</th>
				<th><i class="fas fa-bars"></i> Category</th>
				<th><i class="fas fa-bars"></i> Price</th>
				<th><i class="fas fa-bars"></i> Location</th>
				<th><i class="fas fa-bars"></i> Serial Number</th>
				<th><i class="fas fa-bars"></i> Model</th>
				<th><i class="fas fa-bars"></i> Date</th>
				<th><i class="fas fa-bars"></i> Worth</th>
			</tr>
		</thead>
		<c:forEach items="${productList}" var="product">
			<tr>
				<td>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value=""
							id="tableCheckBox">
					</div>
				<td class="code">${product.code}</td>
				<td class="alt">${product.name}</td>
				<td class="alt">${product.catagory}</td>
				<td class="alt">$ ${product.price}</td>
				<td class="alt">${product.location}</td>
				<td class="alt">${product.serialNumber}</td>
				<td class="alt">${product.model}</td>
				<td class="alt">${product.datePurchased}</td>
			    <td onload="worthLoad();">${product.depreciationVal}</td>
				<td class="trash" onClick="deletes('${product.code}')"><img src="${pageContext.request.contextPath}/trash.png" /></td>
			</tr>
		</c:forEach>
	</table>

<!-- Modal for expired Session -->
<div class="modal fade" id="loggedIn" tabindex="-1" role="dialog"
	aria-labelledby="" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Your Session has expired, would you like to extend it?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Log
					Off</button>
				<button type="button" class="btn btn-primary" data-dismiss="close">Extend</button>
			</div>
		</div>
	</div>
</div>
<script>
document.getElementById("newButton").addEventListener("click", myFunction);

function myFunction() {
 window.location.replace('createProduct');

}
		$(document).ready(function() {
			$("#myInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#myTable tr:not(:first)").filter(function() {
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

	function deletes(code){
	     var post = $.post("productList", {	
				'assetCode' : code
			});
	     post.done(function(response) {
				if (response['errorMessage'] != undefined) {
					BootstrapDialog.show({
						type : BootstrapDialog.TYPE_WARNING,
						title : 'Error',
						message : 'There was an error deleting the Purchase Order\n\n' + response['errorMessage'],
					});
				} else {
					window.location.replace("productList");
				}
	}); 
		}
	</script>

