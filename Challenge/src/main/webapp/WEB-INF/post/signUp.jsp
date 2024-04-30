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
							<img src="./img/1.png" class="d-block w-100" alt=""
								height="200px">
						</div>
						<div class="carousel-item">
							<img src="./img/2.png" class="d-block w-100" alt=""
								height="200px">
						</div>
						<div class="carousel-item">
							<img src="./img/3.png" class="d-block w-100" alt=""
								height="200px">
						</div>
						<div class="carousel-item">
							<img src="./img/4.png" class="d-block w-100" alt=""
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
						<h1>Sign Up</h1>
					</div>
				</div>
				
				<form name="signUpForm" id="signUpForm" action="signUpProcess" method="post">
				
				<div class="row">
					<div class="col">
						<span>ID : </span> <input type="text" name="memberId" id="memberId" class="form-control" maxlength="15" placeholder="Please write down 15 letters or less" autofocus/>
					</div>
				</div>

				<div class="row">
					<div class="col">
						<span>PASSWORD : </span> <input type="password" name="memberPw1" id="memberPw1" class="form-control"/>
					</div>
				</div>
				
				<div class="row">
					<div class="col">
						<span>PASSWORD CHECK : </span> <input type="password" name="memberPw2" id="memberPw2" class="form-control"/>
					</div>
				</div>
				
				<div class="row">
					<div class="col">
						<span>NAME : </span> <input type="text" name="memberName" id="memberName" class="form-control"/>
					</div>
				</div>
				
				<div class="row">
					<div class="col">
						<span>NICK NAME : </span> <input type="text" name="memberNickname" id="memberNickname" class="form-control" maxlength="15" placeholder="Please write down 15 letters or less"/>
					</div>
				</div>
				
				<div class="row">
					<div class="col">
						<span>E-MAIL : </span> <input type="email" name="memberEmail" id="memberEmail" class="form-control"/>
						<br><input type="checkbox" name="memberMailAgree" id="memberMailAgree" />  Agree to receive emails
					</div>
				</div>
				
				<div class="row">
					<div class="col">
						<span>ADDRESS : </span> <input type="text" name="memberAddress" id="memberAddress" class="form-control"/>
					</div>
				</div>
				
				<div class="row">
					<div class="col">
						<span>PHONE NUMBER : </span> <input type="tel" name="memberPhone" id="memberPhone" class="form-control" maxlength="15" placeholder="Please enter the numbers except for '-'"/>
					</div>
				</div>
				
				<button type="submit" class="btn btn-secondary" id="signUpBtn" name="signUpBtn">Sign Up</button>
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