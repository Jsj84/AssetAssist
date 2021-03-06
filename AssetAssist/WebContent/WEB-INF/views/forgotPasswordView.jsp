<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script> 
<title>Password Reset Portal</title>
</head>
<style>
.form-gap {
	padding-top: 70px;
}
</style>
<body>
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
							<h2 class="text-center">Forgot Password?</h2>
							<p>Enter Your Email Address To Reset</p>
							<div class="panel-body">

								<form id="register-form" role="form" autocomplete="off"
									class="form" method="post"
									action="${pageContext.request.contextPath}/forgotPassword">

									<div class="form-group">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-envelope color-blue"></i></span> <input
												id="email" name="emailAddress" placeholder="email address"
												class="form-control" type="email">
										</div>
									</div>
									<div class="form-group">
										<input name="recover-submit"
											class="btn btn-lg btn-primary btn-block" value="Send Link"
											type="submit">
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
	<script> 
   ${showAlert}
   </script>
</body>
</html>