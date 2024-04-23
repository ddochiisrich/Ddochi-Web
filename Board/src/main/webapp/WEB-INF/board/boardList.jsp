<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko" data-bs-theme="dark">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시 글 리스트</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<!-- header  -->
		<%@ include file="../pages/header.jsp"%>
		<!-- content -->
		<div class="row my-5 text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">게시 글 리스트</h2>
			</div>
		</div>
		<form name="searchForm" id="searchForm" action="#"
			class="row justify-content-center my-3">
			<div class="col-auto">
				<select name="type" class="form-select">
					<option value="title">제목</option>
					<option value="writer">작성자</option>
					<option value="content">내용</option>
				</select>
			</div>
			<div class="col-4">
				<input type="text" name="keyword" class="form-control" id="keyword" />
			</div>
			<div class="col-auto">
				<input type="submit" value="검 색" class="btn btn-primary" />
			</div>
		</form>

		<c:if test="${searchOption}">
			<div class="row my-3">
				<div class="col text-center">
					"${ keyword }" 검색 결과
				</div>
			</div>
			<div class="row">
				<div class="col-6">
					<a href="boardList" class="btn btn-outline-success">리스트</a>
				</div>
				<div class="col-6 text-end">
					<a href="writeForm" class="btn btn-outline-success">글쓰기</a>
				</div>
			</div>
		</c:if>


		<c:if test="${ not searchOption}">
			<div class="row my-3">
				<div class="col text-end">
					<a href="writeForm" class="btn btn-outline-success">글쓰기</a>
				</div>
			</div>
		</c:if>
		<div class="row my-3">
			<div class="col">
				<table class="table table-hover">
					<thead>
						<tr class="table-dark">
							<th>NO</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody class="text-secondary">
						<c:if test="${ searchOption and not empty bList }">
							<c:forEach var="b" items="${bList}">
								<tr>
									<td>${ b.no }</td>
									<td>
										<a href="boardDetail?no=${b.no}&pageNum=${currentPage}&type=${type}&keyword=${keyword}"
										class="text-decoration-none link-body">${ b.title }</a>
									</td>
									<td>${ b.writer }</td>
									<td><fmt:formatDate value="${ b.regDate }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${ b.readCount }</td>
								</tr>
							</c:forEach>
						</c:if>

						<!-- 일반 게시글 요청리스트이면서 게시글이 존재하는 경우 -->

						<c:if test="${ not searchOption and not empty bList }">
							<c:forEach var="b" items="${bList}">
								<tr>
									<td>${ b.no }</td>

									<td>
										<a href="boardDetail?no=${b.no}&pageNum=${currentPage}"
											class="text-decoration-none link-body">${ b.title }</a>
									</td>
									<td>${ b.writer }</td>
									<td><fmt:formatDate value="${ b.regDate }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${ b.readCount }</td>
								</tr>
							</c:forEach>
						</c:if>
						<%-- 검색 요청이면서 게시 글이 없는 경우 --%>
						<c:if test="${ searchOption and empty bList }">
							<tr>
								<td colspan="5" class="text-center">
									"${ keyword }"가 포함된 게시
										글이 존재하지 않습니다.
								</td>
							</tr>
						</c:if>
						<%-- 일반 게시글 요청이면서 게시 글이 없는 경우 --%>
						<c:if test="${ not searchOption and empty bList }">
							<tr>
								<td colspan="5" class="text-center">게시 글이 존재하지 않습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<!--  일반 게시글 리스트 요청이면서 게시 글이 존재하는 경우  -->
		<c:if test="${ searchOption and not empty bList }">
		<div class="row">
			<div class="col">
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center">
						<c:if test="${ startPage > pageGroup }">
							<li class="page-item"><a class="page-link"
								href="boardList?pageNum=${ startPage - pageGroup }&type=${type}&keyword=${keyword}">Pre</a></li>
						</c:if>

						<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
							<!--  현제페이지가 맞는경우 -->
							<c:if test="${ i == currentPage }">
								<li class="page-item active" aria-current="page">
									<span class="page-link">${ i }</span>
								</li>

							</c:if>
							<!--  현제 페이지가 아닌경우 -->
							<c:if test="${i != currentPage }">
								<li class="page-item">
								<a class="page-link"
									href="boardList?pageNum=${ i }&type=${type}&keyword=${keyword}">${ i }</a>
								</li>
							</c:if>
						</c:forEach>
						<c:if test="${ endPage < pageCount }">
							<li class="page-item">
							<a class="page-link"
								href="boardList?pageNum=${ startPage + pageGroup }&type=${type}&keyword=${keyword}">Next</a></li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>
		</c:if>
		<c:if test="${ not searchOption and not empty bList }">
		<div class="row">
			<div class="col">
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center">
						<c:if test="${ startPage > pageGroup }">
							<li class="page-item"><a class="page-link"
								href="boardList?pageNum=${ startPage - pageGroup }">Pre</a></li>
						</c:if>

						<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
							<!--  현제페이지가 맞는경우 -->
							<c:if test="${ i == currentPage }">
								<li class="page-item active" aria-current="page">
									<span class="page-link">${ i }</span>
								</li>

							</c:if>
							<!--  현제 페이지가 아닌경우 -->
							<c:if test="${i != currentPage }">
								<li class="page-item">
								<a class="page-link"
									href="boardList?pageNum=${ i }">${ i }</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${ endPage < pageCount }">
							<li class="page-item"><a class="page-link"
								href="boardList?pageNum=${ startPage + pageGroup }">Next</a>
								</li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>
		</c:if>
		<!-- footer  -->
		<%@ include file="../pages/footer.jsp"%>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>