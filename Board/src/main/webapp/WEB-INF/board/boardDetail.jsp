<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>Board Detail</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.7.1.min.js"></script>
<script src="js/formcheck.js"></script>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@ include file="../pages/header.jsp"%>
		<!-- Content -->
		<div class="row my-5" id="global-content">
			<div class="col">
				<form name="checkForm" id="checkForm">
					<input type="hidden" name="no" id="no" value="${ board.no }" /> 
					<input type="hidden" name="pass" id="rPass" />
					<input type="hidden" name="pageNum" id="pageNum" value="${ pageNum }"/>
				</form>
				<div class="row text-center">
					<div class="col">
						<h2 class="fs-3 fw-bold">Board Detail</h2>
					</div>
				</div>
				<div class="row my-3">
					<div class="col">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<th class="table-secondary">Title</th>
									<td colspan="3">${ board.title }</td>
								</tr>
								<tr>
									<th>writer</th>
									<td>${ board.writer }</td>
									<th>date</th>
									<td><fmt:formatDate value="${ board.regDate }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
								<tr>
									<th>password</th>
									<td>
										<div class="col-sm-8">
											<input class="form-control" type="password" name="pass"
												id="pass">
										</div>
									</td>
									<th>view</th>
									<td>${ board.readCount }</td>
								</tr>
								<tr>
									<th>file</th>
									<td colspan="3">
									<c:if test="${ empty board.file1 }">
										 not found file
									</c:if>
										<c:if test="${ not empty board.file1 }">
											<a href="upload/${ board.file1 }">${ board.file1 }</a>
										</c:if></td>
								</tr>
								<tr>
									<td colspan="4"><pre>${ board.content }</pre></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row my-3">
					<div class="col text-center">
						<input class="btn btn-warning mx-3" type="button" id="detailUpdate" value="Edit" /> 
						<input class="btn btn-danger mx-3" type="button" id="detailDelete" value="Delete" />
						<input class="btn btn-primary mx-3" type="button" value="List" onclick="location.href='boardList?pageNum=${ pageNum }'" />
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<%@ include file="../pages/footer.jsp"%>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>