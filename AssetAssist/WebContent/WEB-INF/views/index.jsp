<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1">
 <jsp:include page="cssFiles.jsp"></jsp:include>
</head>
<body>
 <jsp:include page="jsFiles.jsp"></jsp:include>
<nav class="navbar navbar-expand-lg sticky-top bg-primary">
	<a class="navbar-brand" href="${pageContext.request.contextPath}/home"><img
		src="${pageContext.request.contextPath}/logo.jpeg" width="70"
		height="40" alt="" ></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
	<ul class="navbar-nav mr-auto">
		</ul>
		
		 <img src="data:image/jpeg;base64,${user.pic}" class="rounded-circle" height="42" width="42" />

		<div class="btn-group" style="padding-left: 10px; margin-right: 5px;" >
			<button type="button" class="btn btn-primary dropdown-toggle"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				Hello, ${user.firstName}</button>		
			<div class="dropdown-menu" style="margin-right:10px;" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/userObject"><span class="fa fa-user fa-fw mr-3"></span> Profile</a>
					<a class="dropdown-item" href="${pageContext.request.contextPath}/userObject"><span class="fa fa-user fa-fw mr-3"></span> Impersonate</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" id="logoff" href="${pageContext.request.contextPath}/logoff"><span class="fas fa-chalkboard-teacher fas-fw mr-3"></span> Log Off </a>
			</div>
		</div>
	</div>
</nav>
<div class="row">

		<jsp:include page="_sideMenu.jsp" ></jsp:include>

		<div class="col">
		<div class="container-fluid" style="overflow:scroll; height:95%; margin-top: 5px; margin-bottom: 5px;">

			<jsp:include page="${page}"></jsp:include>
		</div>
		</div>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>