<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>User Profile</title>
<style>
.picture-container {
	position: relative;
	cursor: pointer;
	text-align: center;
}

.picture {
	width: 206px;
	height: 206px;
	background-color: #999999;
	border: 4px solid #CCCCCC;
	color: #FFFFFF;
	border-radius: 50%;
	margin: 0px auto;
	overflow: hidden;
	transition: all 0.2s;
	-webkit-transition: all 0.2s;
}

.picture:hover {
	border-color: #2ca8ff;
}

.content.ct-wizard-green .picture:hover {
	border-color: #05ae0e;
}

.content.ct-wizard-blue .picture:hover {
	border-color: #3472f7;
}

.content.ct-wizard-orange .picture:hover {
	border-color: #ff9500;
}

.content.ct-wizard-red .picture:hover {
	border-color: #ff3b30;
}

.picture input[type="file"] {
	cursor: pointer;
	display: block;
	height: 100%;
	left: 0;
	opacity: 0 !important;
	position: absolute;
	top: 0;
	width: 100%;
}

.picture-src {
	width: 100%;
}
</style>
</head>

<div class="container-fluid" style="margin-top: 20px;">
	<!-- form user info -->
	<form class="needs-validation" novalidate role="form"
		autocomplete="off" method="POST"
		action="${pageContext.request.contextPath}/newUser">
		<div class="row">
			<div class="col-md-6">

				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">User
						Name</label>
					<div class="col-lg-9">
						<input class="form-control" type="text" id="userID" name="userID" required>
						<small id="passwordHelpInline" class="text-muted">Please use a valid email address </small>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">First
						name</label>
					<div class="col-lg-9">
						<input class="form-control" id="firstName" name="firstName"
							type="text" required>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">Last
						name</label>
					<div class="col-lg-9">
						<input class="form-control" id="lastName" name="lastName"
							type="text" required>
					</div>
				</div>

				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">Secondary
						Email</label>
					<div class="col-lg-9">
						<input type="email"
							class="form-control" required>
					</div>
				</div>

				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">Phone
						Number</label>
					<div class="col-lg-9">
						<input class="form-control" type="tel" id="phone_number"
							name="phone_number">
					</div>
				</div>

				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">Time
						Zone</label>
					<div class="col-lg-9">
						<select id="time_zone" name="time_zone" class="form-control"
							size="0">
							<c:forEach items="${timezones}" var="zones">
								<option value="${zones}">${zones}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">Password</label>
					<div class="col-lg-9">
						<input class="form-control" name="password" type="password"
							aria-describedby="passwordHelpInline" required> <small
							id="passwordHelpInline" class="text-muted"> Must be 8-20
							characters long. </small>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">Confirm
						Password</label>
					<div class="col-lg-9">
						<input class="form-control" type="password" name="confirmPassword"
							required>
					</div>
				</div>

			</div>
			<div class="col">
		<div class="alert alert-success alert-dismissible fade show"  role="alert" id="profileAlert">
  <strong>Success!</strong> Your profile picture has been successfully updated
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>
				<div class="col">	
			<div class="form-group row">
				<div class="picture">
					<img src="avatar.png" class="picture-src" id="wizardPicturePreview">
					<input type="file" id="file" class="form-control" name="file">
				</div>
			</div>

			<div class="form-group row">
				<label class="custom-file-label" id="fileRead" style="margin-top: 250;" for="file">Browse
					for an image..</label>
			</div>
		</div>
		</div>
		</div>
				<div class="form-group row">
			<label class="col-lg-3 col-form-label form-control-label"></label>
			<div class="col-lg-9">

				<input type="submit" class="btn btn-primary" value="Create User">
			</div>
		</div>
	</form>
</div>


<script>
	$(document).ready(function() {
		$("#file").change(function() {
			readURL(this);
		});
	});
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#wizardPicturePreview').attr('src', e.target.result).fadeIn(
						'slow');
			}
			reader.readAsDataURL(input.files[0]);
			var fileLabel = document.getElementById('fileRead');
			fileLabel.innerHTML = input.files[0].name;
		}

	}
</script>