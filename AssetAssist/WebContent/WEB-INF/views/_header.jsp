
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

		<div class="btn-group">
			<button type="button" class="btn btn-primary dropdown-toggle"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				Hello, ${user.firstName}</button>		
			<ul class="dropdown-menu" style="margin-right:15px; padding: 5px;" aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item" href="${pageContext.request.contextPath}/userObject"><span class="fa fa-user fa-fw mr-3"></span>Profile</a></li>
					<li><a class="dropdown-item"  href="${pageContext.request.contextPath}/userObject"><span class="fa fa-user fa-fw mr-3"></span>Login As</a></li>
                   <li role="separator" class="divider"></li>
				<li><a class="dropdown-item"  href="${pageContext.request.contextPath}/logoff"><span class="fas fa-chalkboard-teacher fas-fw mr-3"></span>Log Off</a> </li>
			</ul>
		</div>
	</div>
