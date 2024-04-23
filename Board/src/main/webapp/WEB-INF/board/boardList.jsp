<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>Board List</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@ include file="../pages/header.jsp"%>
		<!-- Content -->
		<div class="row my-5 text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">Board List</h2>
			</div>
		</div>
		<form name="searchForm" id="searchForm" action="#"
			class="row justify-content-center my-3">
			<div class="col-auto">
				<select name="type" class="form-select">
					<option value="title">TITLE</option>
					<option value="writer">WRITER</option>
					<option value="content">CONTENT</option>
				</select>
			</div>
			<div class="col-4">
				<input type="text" name="keyword" class="form-control" id="keyword" />
			</div>
			<div class="col-auto">
				<input type="submit" value="검 색" class="btn btn-primary" />
			</div>
		</form>
		<div class="row">
			<div class="col text-end">
				<a href="writeForm" class="btn btn-outline-success">POST</a>
			</div>
		</div>
		<div class="row my-3">
			<div class="col">
				<table class="table table-hover">
					<thead>
						<tr class="table-dark">
							<th>NO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>DATE</th>
							<th>VIEW</th>
						</tr>
					</thead>
					<tbody class="text-secondary">
						<c:if test="${ not empty bList }">
							<c:forEach var="b" items="${bList}" varStatus="status">
								<tr>
									<td>${ b.no }</td>
									<td><a
										href="boardDetail?no=${b.no}&pageNum=${currentPage}"
										class="text-decoration-none link-secondary">${ b.title }</a></td>
									<td>${ b.writer }</td>
									<td><fmt:formatDate value="${ b.regDate }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${ b.readCount }</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty bList }">
							<tr>
								<td colspan="5" class="text-center">Content does not exist.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center">
						<c:if test="${ startPage > pageGroup }">
							<li class="page-item"><a class="page-link" href="boardList?pageNum=${ startPage - pageGroup }">Pre</a></li>
						</c:if>
						
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:if test="${i == currentPage }">
								<li class="page-item active" aria-current="page"><span class="page-link">${i}</span></li>
							</c:if>
							<c:if test="${i != currentPage }">
								<li class="page-item"><a class="page-link"
									href="boardList?pageNum=${ i }">${i}</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${ endPage < pageCount }">
							<li class="page-item"><a class="page-link" href="boardList?pageNum=${ startPage + pageGroup }">Next</a></li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>
		<!-- Footer -->
		<%@ include file="../pages/footer.jsp"%>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>