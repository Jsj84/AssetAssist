<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<style>
.form-row {
	padding: 5px;
}
</style>
</head>
	<div class="row">
		  <div class="col-lg">
		        <!-- form user info -->
                   
			<form class="needs-validation" novalidate id="myform" method="POST" action="${pageContext.request.contextPath}/createProduct">
				<div class="form-row">
					<label for="asset_number" class="col-sm-3 col-form-label">Asset
						#</label>
					<div class="col">
						<input type="text" class="form-control" id="asset_number"
							placeholder="Asset Number" name="code" required>
					</div>
				</div>
				<div class="form-row">
					<label for="catagory" class="col-sm-3 col-form-label">Category</label>
					<div class="col">
						<div class="input-group">
							<div class="input-group-prepend">
								<button class="btn btn-primary btn-sm" id="category" data-toggle="modal" data-target="addModal "type="button">Add</button>
							</div>

							<select class="custom-select" id="catagory" name="catagory">
								<option value="Lap Top">Lap Top</option>
								<option value="Desk Top">Desk Top</option>
								<option value="Printer / Scanner">Printer / Scanner</option>
							</select>
						</div>
					</div>
				</div>
				<div class="form-row">
					<label for="serialnumber" class="col-sm-3 col-form-label">Serial
						#</label>
					<div class="col">
						<input type="text" class="form-control" id="serialnumber"
							placeholder="Serial Number" name="serialnumber">
					</div>
				</div>
				<div class="form-row">
					<label for="modelnumber" class="col-sm-3 col-form-label">Model
						#</label>
					<div class="col">
						<input type="text" class="form-control" id="modelnumber"
							placeholder="model number" name="modelnumber">
					</div>
				</div>
				<div class="form-row">
					<label for="name" class="col-sm-3 col-form-label">Description</label>
					<div class="col">
						<input type="text" class="form-control" id="name"
							placeholder="Description" onClick="hpapi()" name="name" required>
					</div>
				</div>

				<div class="form-row">
					<label for="price" class="col-sm-3 col-form-label">Cost</label>
					<div class="col">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">$</span>
							</div>
							<input type="number" class="form-control" id="price"
								placeholder="Cost at purchase" name="price"
								aria-label="Amount (to the nearest dollar)">
						</div>
					</div>
				</div>
				
								<div class="form-row">
					<label for="yearsDep" class="col-sm-3 col-form-label">Depreciation</label>
					<div class="col">
						<div class="input-group">
							<input type="number" class="form-control" id="yearsDep"
								placeholder="Years of depreciation" name="yearsDep" >
						</div>
					</div>
				</div>
				
				<div class="form-row">
					<label for="location" class="col-sm-3 col-form-label">Location</label>
					<div class="col">
						<input type="text" class="form-control" id="location"
							placeholder="Location " name="location">

					</div>
				</div>
				<div class="form-row">
					<label for="date" class="col-sm-3 col-form-label">Date</label>
					<div class="col">
						<div class="form-group">
							<input type="date" class="form-control" id="date" name="date">
						</div>
					</div>
				</div>
				<div class="form-row" id="status">
					<label for="warrenteeStatus" class="col-sm-3 col-form-label">Warranty
						Status</label>
					<div class="col">
						<input type="text" class="form-control" id="warrenteeStatus"
							name="warrenteeStatus" disabled>
					</div>
				</div>
				<div class="form-row" id="start">
					<label for="warrenteeStartDate" class="col-sm-3 col-form-label">Warranty
						Start: </label>
					<div class="col">
						<input type="date" class="form-control" id="warrenteeStartDate"
							name="warrenteeStartDate" disabled>
					</div>
				</div>
				<div class="form-row" id="end">
					<label for="warrenteeStartDate" class="col-sm-3 col-form-label">Warranty
						End: </label>
					<div class="col">
						<input type="date" class="form-control" id="warrenteeEndDate"
							name="warrenteeEndDate" disabled>
					</div>
				</div>
				<a class="btn btn-outline-danger" role="button" href="${pageContext.request.contextPath}/productList">Cancel</a>
      <button type="submit" class="btn btn-outline-success">Submit</button>
			</form>
		</div>
</div>

<div class="modal" tabindex="-1" role="dialog" labelledby="addModal" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Add/Remove or Delete a Category</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
<!--      Model Body -->
<div class="input-group">
  <input type="text" class="form-control" aria-label="Text input with segmented dropdown button">
  <div class="input-group-append">
    <button type="button" class="btn btn-outline-secondary">Action</button>
    <button type="button" class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      <span class="sr-only">Toggle Dropdown</span>
    </button>
    <div class="dropdown-menu">
      <a class="dropdown-item" href="#">Delete</a>
      <a class="dropdown-item" href="#">Hide</a>
    </div>
  </div>
</div>
<!-- End Model Body -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
      </div>
    </div>
  </div>
</div>

<script>

$('#addModal').on('show.bs.modal', function (event) {
	var button = $(event.modal)
	  var recipient = button.data('whatever') 

	  var modal = $(this)
	  modal.find('.modal-title').text('New message to ' + recipient)
	  modal.find('.modal-body input').val(recipient)
	});
	
$('#date').datepicker({
	uiLibrary: 'bootstrap4'
});
        $(document).ready(function(){
        	if ($('#warrenteeStatus').val() == "" || $("#warrenteeStatus").val() == null) {
        		$('#status').hide();
        		$('#start').hide();
        		$('#end').hide();
        	}
        	 else {
        		 $('#status').show();
        		$('#start').show();
        		$('#end').show(); 
        	}
        		
        	});
    </script>