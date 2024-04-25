<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>📕 Challenge BLOG 📕</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"
	integrity="sha512-10/jx2EXwxxWqCLX/hHth/vu2KY3jCF70dCQB8TSgNjbCVAC/8vai53GfMDrO2Emgwccf2pJqxct9ehpzG+MTw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<body>
	<div class="container">
		<%@ include file="../page/header.jsp" %>
		<div class="row">
			<%@ include file="../page/leftSide.jsp" %>
			<div class="col-9">
				<!-- 캐러셀 -->
				<div id="carouselExampleAutoplaying" class="carousel slide mb-3"
					data-bs-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="./img/carousel8.jpg" class="d-block w-100" alt=""
								height="200px">
						</div>
						<div class="carousel-item">
							<img src="./img/carousel9.jpg" class="d-block w-100" alt=""
								height="200px">
						</div>
						<div class="carousel-item">
							<img src="./img/carousel10.jpg" class="d-block w-100" alt=""
								height="200px">
						</div>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>

				<div class="row">
					<div class="col d-flex justify-content-end">
						<button type="button" class="btn btn-secondary btn-sm"
							style="width: 100px;">List Type</button>
						&nbsp;
						<button type="button" class="btn btn-secondary btn-sm"
							style="width: 100px;">Gallery Type</button>
					</div>
				</div>

				<div class="row my-2">
					<div class="col d-flex justify-content-end">
						<span>post count : </span> &nbsp; <select
							class="form-select form-select-sm"
							aria-label="Small select example" style="width: 70px;">
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
						</select> &nbsp;
						<button type="button" class="btn btn-secondary btn-sm"
							style="width: 40px;">GO</button>
					</div>
				</div>

				<div class="row">
					<div class="col-6">
						<h4>All Post</h4>
					</div>
					<div class="col-6 d-flex justify-content-end">
						<button type="button" class="btn btn-secondary btn-lg mb-2">POST</button>
					</div>
				</div>

				<table class="table table-hover">
					<tr class="table-dark">
						<th style="width: 3%" >No</th>
						<th style="width: 3%" ></th>
						<th>Title</th>
						<!-- <th>Content</th> -->
						<th style="width: 15%">RegDate</th>
						<th style="width: 5%">Like</th>
						<th style="width: 5%">View</th>
						<th style="width: 3%"></th>
					</tr>
					<c:forEach var="p" items="${ cPost }">
						<tr class="my-3">
							<td>${ p.postNo }</td>
							<td></td>
							<td><a href="postDetail?no=${ p.postNo }">${ p.postTitle }</a></td>
							<%-- <td>${ p.postContent }</td> --%>
							<td><fmt:formatDate value="${ p.postRegDate }"
									pattern="MM-dd HH:mm" /></td>
							<td>${ p.like1 }</td>
							<td>${ p.postView }</td>
							<td><a href="#"><i class="fa-solid fa-trash"></i></a></td>
						</tr>
					</c:forEach>
				</table>
				<div class="row">
					<div class="col d-flex justify-content-center">
						<nav aria-label="Page navigation example">
							<ul class="pagination">
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
				
				<!-- Footer -->
				<hr>
				<%@ include file="../page/footer.jsp" %>
			</div>
		</div>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>