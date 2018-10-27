<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<!-- <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>  -->
<title>Login</title>

<style type="text/css">
/*  @import "compass/css3"; */
body {
	padding: 50px;
}

.colorgraph {
	height: 5px;
	border-top: 0;
	background: #c4e17f;
	border-radius: 5px;
	background-image: -webkit-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%,
		#f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%,
		#db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%,
		#669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
	background-image: -moz-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%,
		#f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%,
		#db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%,
		#669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
	background-image: -o-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca
		25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe
		50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1
		87.5%, #62c2e4 87.5%, #62c2e4);
	background-image: linear-gradient(to right, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca
		25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe
		50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1
		87.5%, #62c2e4 87.5%, #62c2e4);
}
</style>
</head>
<html>
<body>
	<script
		src="${pageContext.request.contextPath}/js/loginWindowScreen.js"></script>
	<div class="container">

		<div class="row" style="margin-top: 20px">
			<div
				class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
				<form role="form" method="POST"
					action="${pageContext.request.contextPath}/login">
					<fieldset>
						<div class="alert alert-${errorType}">
							<strong>${errorType}</strong> ${errorString}
						</div>
						<h2>Please Sign In</h2>
						<hr class="colorgraph">
						<div class="form-group">
							<input type="email" name="userName" id="email"
								class="form-control input-lg" value="${userName}"
								placeholder="Email Address">
						</div>
						<div class="form-group">
							<input type="password" name="password" id="password"
								class="form-control input-lg" value="${user.password}"
								placeholder="Password">
						</div>
						<span class="label-checkbox"> <label>Remember Me <input
								type="checkbox" name="rememberMe" id="remember_me"
								checked="checked"></label> <a
							href="${pageContext.request.contextPath}/forgotPassword"
							class="btn btn-link pull-right">Forgot Password?</a>
						</span>
						<hr class="colorgraph">
						<input type="submit" class="btn btn-lg btn-success btn-block"
							value="Sign In">
					</fieldset>
				</form>
			</div>
		</div>

	</div>
</body>
<script> 
   ${showAlert}
   </script>
</html>
