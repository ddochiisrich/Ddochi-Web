<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>🧛🏻 Board List 🧛🏻</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.7.1.min.js"></script>
<script src="js/formCheck02.js"></script>
</head>
<body>
	<div class="container">
		<!-- header start -->
		<%@ include file="../pages/header.jsp"%>
		<!-- header end -->

		<!-- content -->
		<div class="row my-5" id="global-content">
			<div class="col">

				<div class="row text-center">
					<div class="col">
					<!-- Hide Form -->
					<form name="checkForm" id="checkForm">
						<input type="hidden" name="no" id="no" value="${ board.no }">
						<input type="hidden" name="pass" id="rPass">
					</form>
					
						<h2 class="fs-3 fw-bold">Board Detail</h2>
					</div>
				</div>
				<div class="row my-3">
					<div class="col">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<th>TITLE</th>
									<td colspan="3">${ board.title }</td>
								</tr>
								<tr>
									<th>WRITER</th>
									<td>${ board.writer }</td>
									<th>DATE</th>
									<td><fmt:formatDate value="${ board.regDate }"
											pattern="yyyy-MM-dd HH:mm" /></td>
								</tr>
								<tr>
									<th>PASSWORD</th>
									<div class="col-8">
										<td><input class="form-control" type="password"
											name="pass" id="pass"></td>
									</div>
									<th>VIEWS</th>
									<td>${ board.readCount }</td>
								</tr>
								<tr>
									<th>FILE</th>
									<td colspan="3"><c:if test="${ empty board.file1 }">
											Not Found File
										</c:if> <c:if test="${ not empty board.file1 }">
											 <a href="upload/${ board.file1 }">${ board.file1 }</a>
										</c:if></td>
								</tr>
								<tr>
									<td colspan="4" style="white-space: pre">${ board.content }</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row my-3">
					<div class="col text-center">
						<input type="button" class="btn btn-danger" id="detailUpdate"
							value=" Edit "> &nbsp;&nbsp; 
							<input type="button"
							class="btn btn-danger" id="detailDelete" value="Delete">
						&nbsp;&nbsp; <input type="button" class="btn btn-danger"
							value=" List " onclick="location.href='boardList'">
					</div>
				</div>
			</div>
		</div>
		<!-- footer start -->
		<%@ include file="../pages/footer.jsp"%>
		<!-- footer end -->
		<script src="bootstrap/bootstrap.bundle.min.js"></script>
	</div>
</body>
</html>