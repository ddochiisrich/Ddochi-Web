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
<script src="js/jquery-3.7.1.min.js"></script>
<script src="js/formCheck.js"></script>
</head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"
	integrity="sha512-10/jx2EXwxxWqCLX/hHth/vu2KY3jCF70dCQB8TSgNjbCVAC/8vai53GfMDrO2Emgwccf2pJqxct9ehpzG+MTw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<body>
	<div class="container">
		<%@ include file="../page/header.jsp"%>
		<div class="row">
			<%@ include file="../page/leftSide.jsp"%>
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
				<div class="row text-center">
					<div class="col">
						<h1>Edit Post</h1>
					</div>
				</div>

				<form name="postForm" action="updateProcess" id="postForm"
					method="post" ${ empty post.postFile ? "" : "enctype='multipart/form-data'"}>
					<input type="hidden" name="updatePageNum" value="${pageNum}" /> 
					<input type="hidden" name="updateNo" value="${ post.postNo }">
					
					<c:if test="${ searchOption }">
						<input type="hidden" name="type" value="${ type }">	
						<input type="hidden" name="keyword" value="${ keyword }">
					</c:if>
					
					<div class="row">
						<div class="col-2"></div>
						<div class="col-8">
							<label for="postTitle" class="form-label">Title</label> <input
								type="text" class="form-control" id="postTitle" name="postTitle"
								value="${ post.postTitle }">
						</div>
						<div class="col-2"></div>
					</div>
					<div class="row">
						<div class="col-2"></div>
						<div class="col-8">
							<label for="postContent" class="form-label">Content</label>
							<textarea class="form-control" style="height: 500px;"
								id="postContent" name="postContent">${ post.postContent }</textarea>
						</div>
						<div class="col-2"></div>
					</div>

					<c:if test="${empty post.postFile}">
						<div class="col-8 offset-md-2">
							<label for="updatePostFile" class="form-label">File</label>
							<input type="file" class="form-control" name="updatePostFile" id="updatePostFile">
						</div>
					</c:if>

					<div class="row">
						<div class="col-2"></div>
						<div class="col-8 my-3 d-flex justify-content-center">
							<button type="submit" class="btn btn-secondary"
								style="width: 100px;">Post</button>
							&nbsp;&nbsp;&nbsp;
							<c:if test="${ not searchOption }">
							<button type="button" class="btn btn-secondary"
								style="width: 100px;" onclick="location.href='postMain?pageNum=${pageNum}'">List</button>
								</c:if>
							<c:if test="${ searchOption }">
							<button type="button" class="btn btn-secondary"
								style="width: 100px;" onclick="location.href='postMain?pageNum=${ pageNum }&type=${ type }&keyword=${ keyword }'">List</button>
								</c:if>	
						</div>
						<div class="col-2"></div>
					</div>

				</form>
				<!-- Footer -->
				<hr>
				<%@ include file="../page/footer.jsp"%>
			</div>
		</div>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>