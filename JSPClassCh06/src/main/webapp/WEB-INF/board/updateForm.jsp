<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.jspstudy.ch06.vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

		<!-- content start -->
		<div class="row my-5" id="global-content">
			<div class="col">
				<div class="row">
					<div class="col">
						<H1 class="text-center fw-bold fs-3">🧛🏻 Edit Post 🧛🏻</H1>
					</div>
				</div>
				
				<form name="updateForm" id="updateForm" action="updateProcess"
					method="post" class="row border-danger g-3 my-3">
					<input type="hidden" name="no" value="${ board.no }">
					<div class="col-md-4 offset-2">
						<label for="writer" class="form-label">Writer</label> <input type="text" class="form-control" id="writer" name="writer" value="${ board.writer }" placeholder="write user name!">
					</div>
					<div class="col-md-4">
						<label for="pass" class="form-label">Password</label> <input type="password" class="form-control" id="pass" name="pass" placeholder="put your password!">
					</div>
					<div class="col-8 offset-2">
						<label for="title" class="form-label">Title</label> <input type="text" class="form-control" id="title" name="title" value="${ board.title }">
					</div>
					<div class="col-8 offset-2">
						<label for="title" class="form-label">Content</label> <textarea class="form-control" id="content" name="content" rows="10"> ${ board.content }</textarea>
					</div>
					<div class="col-8 offset-2 text-center mt-5">
						<input type="submit" value="Edit" class="btn btn-danger">
						&nbsp;&nbsp;
						<input type="button" value="List" class="btn btn-danger" onclick="location.href='boardList'">
					</div>
				</form>

			</div>
		</div>
		<!-- content end -->

		<!-- footer start -->
		<%@ include file="../pages/footer.jsp"%>
		<!-- footer end -->
		<script src="bootstrap/bootstrap.bundle.min.js"></script>
	</div>
</body>
</html>