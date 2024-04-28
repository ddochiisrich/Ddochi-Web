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
						<button type="button" class="btn btn-secondary btn-lg mb-2"
							onclick="location.href='postForm'">POST</button>
					</div>
				</div>

				<c:if test="${ searchOption }">
					<div class="row my-3">
						<div class="col text-center">"${ keyword }" 검색 결과</div>
					</div>
					<div class="row my-3">
						<div class="col">
							<a href="boardList" class="btn btn-outline-success">리스트</a>
						</div>

					</div>
				</c:if>
				<!-- 검색 요청이 아닐 경우 아래를 화면에 표시 -->
				<c:if test="${ not searchOption }">
					<div class="row my-3">
						<div class="col text-end">
							<a href="#">&nbsp;</a>
						</div>
					</div>
				</c:if>


				<table class="table table-hover">
					<tr class="table-dark">
						<th style="width: 3%">No</th>
						<th style="width: 3%"></th>
						<th>Title</th>
						<!-- <th>Content</th> -->
						<th style="width: 15%">Writer</th>
						<th style="width: 15%">RegDate</th>
						<th style="width: 5%">Like</th>
						<th style="width: 5%">View</th>
					</tr>
					<c:if test="${ searchOption and not empty cPost }">
						<c:forEach var="p" items="${ cPost }" varStatus="status">
							<tr class="my-3">
								<td>${ p.postNo }</td>
								<td></td>
								<td><a
									href="postDetail?no=${ p.postNo }&pageNum=${currentPage}&type=${type}&keyword=${keyword}">${ p.postTitle }</a></td>
								<td>${ p.writer }</td>
								<td><fmt:formatDate value="${ p.postRegDate }"
										pattern="MM-dd HH:mm" /></td>
								<td>${ p.like1 }</td>
								<td>${ p.postView }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${ searchOption and not empty cPost }">
						<c:forEach var="p" items="${ cPost }" varStatus="status">
							<tr class="my-3">
								<td>${ p.postNo }</td>
								<td></td>
								<td><a
									href="postDetail?no=${ p.postNo }&pageNum=${currentPage}">${ p.postTitle }</a></td>
								<td>${ p.writer }</td>
								<td><fmt:formatDate value="${ p.postRegDate }"
										pattern="MM-dd HH:mm" /></td>
								<td>${ p.like1 }</td>
								<td>${ p.postView }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${ searchOption and empty cPost }">
						<tr>
							<td colspan="5" class="text-center">${ keyword }+ POST does
								not exist. Please contact the admin</td>
						</tr>
					</c:if>
					<c:if test="${ not searchOption and empty cPost }">
						<tr>
							<td colspan="5" class="text-center">POST does not exist.
								Please contact the admin</td>
						</tr>
					</c:if>
				</table>
				<c:if test="${ searchOption and not empty cPost }">
					<div class="row">
						<div class="col">
							<nav aria-label="Page navigation example">
								<ul class="pagination justify-content-center">
									<c:if test="${ startPage > pageGroup }">
										<li class="page-item"><a class="page-link"
											href="boardList?pageNum=${ startPage - pageGroup }&type=${ type }&keyword=${ keyword }"
											aria-label="Previous"> Pre </a></li>
									</c:if>
									
									<c:forEach var="i" begin="${startPage}" end="${endPage}">
										<c:if test="${i == currentPage }">
										</c:if>
										<c:if test="${ i != cuerrentPage }">
											<li class="page-item"><a class="page-link"
												href="boardList?pageNum=${ i }&type=${ type }&keyword=${ keyword }">${i}</a></li>
										</c:if>
									</c:forEach>
									<c:if test="${ endPage < pageCount }">
										<li class="page-item"><a class="page-link" href="boardList?pageNum=${ startPage + pageGroup }&type=${ type }&keyword=${ keyword }">Next</a>
									</c:if>
								</ul>
							</nav>
						</div>
					</div>
				</c:if>
				
				<c:if test="${ not searchOption and not empty cPost }">
					<div class="row">
						<div class="col">
							<nav aria-label="Page navigation example">
								<ul class="pagination justify-content-center">
									<c:if test="${ startPage > pageGroup }">
										<li class="page-item"><a class="page-link"
											href="boardList?pageNum=${ startPage - pageGroup }&type=${ type }&keyword=${ keyword }"
											aria-label="Previous"> Pre </a></li>
									</c:if>
									
									<c:forEach var="i" begin="${startPage}" end="${endPage}">
										<c:if test="${i == currentPage }">
										</c:if>
										<c:if test="${ i != cuerrentPage }">
											<li class="page-item"><a class="page-link"
												href="boardList?pageNum=${ i }&type=${ type }&keyword=${ keyword }">${i}</a></li>
										</c:if>
									</c:forEach>
									<c:if test="${ endPage < pageCount }">
										<li class="page-item"><a class="page-link" href="boardList?pageNum=${ startPage + pageGroup }&type=${ type }&keyword=${ keyword }">Next</a>
									</c:if>
								</ul>
							</nav>
						</div>
					</div>
				</c:if>
					<!-- Footer -->
					<hr>
					<%@ include file="../page/footer.jsp"%>
			</div>
		</div>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>