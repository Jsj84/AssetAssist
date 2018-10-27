<%@page import="core.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<title>Home Page</title>
</head>
		<div class="container-fluid">
		<!-- Heading Row -->
		<div class="row">
		<div class="col">
	
	<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" style="margin-top: 25px;">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-300" src="1.jpeg" alt="First slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-300" src="2.jpeg" alt="Second slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-300" src="3.jpeg" alt="Third slide">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>	
  	</div> 
		</div>	
		<div class="col">
			<canvas id="barChart"></canvas>
	</div>
		</div>
<hr width="100%" size="8" align="center"> 




<div class="row">
  <div class="col-sm-6">
    <div class="card">
      <div class="card-header">
    <ul class="nav nav-tabs card-header-tabs">
      <li class="nav-item">
        <a class="nav-link active" href="${pageContext.request.contextPath}/productList">Printers / Scanners</a>
      </li>
      <li class="nav-item">
        <a class="nav-link active" href="${pageContext.request.contextPath}/productList">Desktops</a>
      </li>
      <li class="nav-item">
        <a class="nav-link active" href="${pageContext.request.contextPath}/productList">Laptops</a>
      </li>
    </ul>
  </div>
      <div class="card-body">
        <h5 class="card-title">Asset Database</h5>
        <p class="card-text">Live display and description on this card via a tab view!</p>
        <a href="#" class="btn btn-primary">All Assets</a>
      </div>
    </div>
  </div>
  <div class="col-sm-6">
    <div class="card">
              <div class="card-header">
    <ul class="nav nav-tabs card-header-tabs">
      <li class="nav-item">
        <a class="nav-link active" href="${pageContext.request.contextPath}/userInfo">Active Users</a>
      </li>
      <li class="nav-item">
        <a class="nav-link active" href="${pageContext.request.contextPath}/userInfo">In Active Users</a>
      </li>
      <li class="nav-item">
        <a class="nav-link active" href="${pageContext.request.contextPath}/userInfo">Recently Deleted</a>
      </li>
    </ul>
  </div>
      <div class="card-body">
        <h5 class="card-title">Users</h5>
        <p class="card-text">Live display and shortcuts to users of the platform.</p>
        <a href="#" class="btn btn-primary">All Users</a>
      </div>
    </div>
  </div>
</div>


		<!-- Content Row -->
		<div class="row" style="margin-top: 25px;">
			<div class="col-md-4 mb-4">
				<div class="card">
					<div class="card-body">
						<div class="card-header">
							<h3 class="card-title">Asset Database</h3>
						</div>
						<ul class="list-group list-group-flush">

							<li
								class="list-group-item d-flex justify-content-between align-items-center">
								Total Assets <span class="badge badge-primary badge-pill">${database}</span>
							</li>

							<c:forEach items="${map}" var="map">
								<c:choose>
									<c:when test="${not empty map.key}">
										<li
											class="list-group-item d-flex justify-content-between align-items-center">
											${map.key} <span class="badge badge-primary badge-pill">${map.value}</span>
										</li>
									</c:when>
								</c:choose>
							</c:forEach>
						</ul>
					</div>
					<div class="card-footer">
						<a href="${pageContext.request.contextPath}/productList"
							class="btn btn-primary">Asset DB</a>
					</div>
				</div>
			</div>

			<!-- /.col-md-4 -->
			<div class="col-md-4 mb-4">
				<div class="card">
					<div class="card-body">
						<div class="card-header">
							<h3 class="card-title">Lease Schedule</h3>
						</div>

					</div>
					<div class="card-footer">
						<a href="#" class="btn btn-primary">Calendar</a>
					</div>
				</div>
			</div>
			<!-- /.col-md-4 -->
			<div class="col-md-4 mb-4">
				<div class="card">
					<div class="card-body">
						<div class="card-header">
							<h3 class="card-title">Evaluate Product</h3>
						</div>

					</div>
					<div class="card-footer">
						<a href="#" class="btn btn-primary">Get Started</a>
					</div>
				</div>
			</div>
			<!-- /.col-md-4 -->
</div>
		</div>
		<!-- /.row -->

	
	<!-- /.container -->
<script>
//bar
var ctxB = document.getElementById("barChart").getContext('2d');

var tempLabels = [];
var tempData = [];

<c:forEach items="${map}" var="map">
tempLabels.push('${map.key}');
tempData.push('${map.value}');
</c:forEach> 
	  
var myBarChart = new Chart(ctxB, {
	
    type: 'bar',
    data: {
        labels: tempLabels, //["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
        datasets: [{
            label: 'Overview of assets',
            data: tempData, //[12, 19, 3, 5, 2, 3],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});
</script>