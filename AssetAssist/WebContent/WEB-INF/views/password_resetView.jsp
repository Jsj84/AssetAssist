<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${pageContext.request.contextPath}/js/loginWindowScreen.js"></script>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>Password Reset Portal</title>
<style>
.form-gap {
	padding-top: 70px;
}

.input-group {
	padding: 5px;
	margin: auto;
}
</style>
</head>
<body style="background-color: #f1f1f1">
	<div class="form-gap"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="text-center">
							<h3>
								<i class="fa fa-lock fa-4x"></i>
							</h3>
							<div class="alert alert-${errorType}">
								<strong>${errorType}</strong> ${errorString}
							</div>
							<h2 class="text-center">Reset Password</h2>
							<p>You can reset your password here.</p>
							<div class="panel-body">
								<form id="register-form" role="form" autocomplete="off"
									class="form" method="post"
									action="${pageContext.request.contextPath}/passwordReset">
									<div class="form-group">
										<div class="input-group">
											<input id="password" name="password"
												placeholder="enter password" class="form-control"
												type="password">
										</div>
										<div class="input-group">
											<input id="confirmPassword" name="confirmPassword"
												placeholder="confirm password" class="form-control"
												type="password">
										</div>
									</div>
									<div class="form-group">
										<input name="recover-submit"
											class="btn btn-lg btn-primary btn-block"
											value="Reset Password" type="submit">
									</div>
									<input type="hidden" class="hide" name="token" id="token"
										value="">
								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script> 
   ${showAlert}
   </script>
</html>