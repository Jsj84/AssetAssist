 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg sticky-top bg-primary">
	<a class="navbar-brand" href="${pageContext.request.contextPath}/home"><img
		src="${pageContext.request.contextPath}/logo.png" width="70"
		height="40" alt="" /></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
	<ul class="navbar-nav mr-auto">
		</ul>
		 <img src="data:image/jpeg;base64,${user.profilePic}" class="profile-image img-circle">
		<div class="btn-group" >
			<button type="button" class="btn btn-primary dropdown-toggle"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				Hello, ${user.firstName}</button>		
			<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/userObject">My Profile</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" id="logoff" href="${pageContext.request.contextPath}/logoff">Log Off</a>
			</div>
		</div>
	</div>
</nav>