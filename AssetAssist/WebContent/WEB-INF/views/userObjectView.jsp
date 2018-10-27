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
.picture{
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
.picture:hover{
    border-color: #2ca8ff;
}
.content.ct-wizard-green .picture:hover{
    border-color: #05ae0e;
}
.content.ct-wizard-blue .picture:hover{
    border-color: #3472f7;
}
.content.ct-wizard-orange .picture:hover{
    border-color: #ff9500;
}
.content.ct-wizard-red .picture:hover{
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
	<div class="row">
		<div class="col-md-6">

			<!-- form user info -->
			<form class="form-group has-success has-feedback" role="form"
				autocomplete="off" method="POST"
				action="${pageContext.request.contextPath}/userObject">
				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">User
						Name</label>
					<div class="col-lg-9">
						<input class="form-control" type="text" value="${user.userName}"
							disabled>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">First
						name</label>
					<div class="col-lg-9">
						<input class="form-control" id="firstName" name="firstName"
							type="text" value="${user.firstName}">
					</div>
				</div>


				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">Last
						name</label>
					<div class="col-lg-9">
						<input class="form-control" id="lastName" name="lastName"
							type="text" value="${user.lastName}">
					</div>
				</div>

				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">Email</label>
					<div class="col-lg-9">
						<input type="email" id="email" name="email" class="form-control"
							value="${user.email}">
					</div>
				</div>

				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">Phone
						Number</label>
					<div class="col-lg-9">
						<input class="form-control" type="number" id="phone_number"
							name="phone_number" value="${user.phone_number}">
					</div>
				</div>

				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">Theme</label>
					<div class="col-lg-9">
						<select id="Theme_Type" name="Theme_Type" class="form-control">
							<option value="${user.theme}">${user.theme}</option>
							<c:forEach items="${themes}" var="themes">
								<option value="${themes}">${themes}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label">Time
						Zone</label>
					<div class="col-lg-9">
						<select id="time_zone" name="time_zone" class="form-control"
							size="0">
							<option value="${user.time_zone}">${user.time_zone}</option>
							<c:forEach items="${timezones}" var="zones">
								<option value="${zones}">${zones}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group row">
				<label class="col-lg-3 col-form-label form-control-label" id="changPW" onclick="changePassword();" style="color:blue;">change password</label>
				</div>
				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label" id="pwLabel" style="display: none;">Password</label>
					<div class="col-lg-9">
						<input class="form-control" name="password" id="pw" style="display: none;" type="password">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-3 col-form-label form-control-label" id="pw2Label" style="display: none;">Confirm Password</label>
					<div class="col-lg-9">
						<input class="form-control" id="pw2" type="password"
							style="display: none;">
					</div>
				</div>

				<div class="form-group row">
				<label class="col-lg-3 col-form-label form-control-label"></label>	
			<div class="col-lg-9">
						<input type="submit" style="float: right;" class="btn btn-primary"
							value="Save Changes">
				</div>
				</div>

			</form>
		</div>
		<div class="col">
		
		<div class="alert alert-success alert-dismissible fade show"  role="alert" id="profileAlert">
  <strong>Success!</strong> Your profile picture has been successfully updated
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>

			<div class="picture-container" style="border:dashed 2px gray">
			<h3>Drop an image in the box.</h3>
				<div class="picture">
					<img src="data:image/jpeg;base64,${user.pic}" class="picture-src"
						id="wizardPicturePreview"> <input type="file" id="file"
						class="form-control" name="file">
				</div>
					<div class="form-group row">
				<label class="custom-file-label" id="fileRead" style="margin-top: 280;" for="file">Browse
					for an image..</label>
			</div>
			</div>
		
		</div>
	</div>

</div>
<script>

$('#profileAlert').hide();

function changePassword(){
	$('#pwLabel').show();
	$('#pw2Label').show();
	$('#pw').show();
	$('#pw2').show();
	$('#changPW').hide();
}

	$('#Theme_Type').change(function() {
		var theme = $(this).val();
		changeT(theme);
	});

	function changeT(theme) {
		$.ajax({
			url : "userObject",
			type : "GET",
			data : {
				'theme' : theme
			},
			success : function(response) {
				document.location.reload(true);
				debugger;
			},
			error : function(error) {
				debugger;
			}
		});
	}
	$(document).ready(function() {
		$("#file").change(function() {
			readURL(this);
			 $('#profileAlert').show();
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
			uploadPic();
		}

	}

	function uploadPic() {
		var fd = new FormData();
		fd.append('filename', document.getElementById("file").files[0]);
		var req;
		if (window.ActiveXObject) {
			req = new ActiveXObject();
		} else {
			req = new XMLHttpRequest();

		}
		req.open("post", "fileHandler", true);
		req.send(fd);
	}
</script>