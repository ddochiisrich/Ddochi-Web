<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>
	<div class="row border-bottom border-primary">
		<div class="col-4">
			<p>
				<img src="https://via.placeholder.com/200x100">
			</p>
		</div>
		<div class="col-8">
			<div class="row">
				<div class="col">
					<ul class="nav justify-content-end">
						<li class="nav-item"><a class="nav-link" href="#">Login</a></li>
						<li class="nav-item"><a class="nav-link" href="boardList">Board list</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Sign up</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Order/Delivery</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Contact us</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col text-end">LogIn Comment Area</div>
			</div>
		</div>
	</div>
</body>
</html>