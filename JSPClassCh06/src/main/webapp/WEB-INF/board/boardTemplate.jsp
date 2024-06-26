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
						<H1 class="text-center fw-bold fs-3">🧛🏻 Board List 🧛🏻</H1>
					</div>
				</div>
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