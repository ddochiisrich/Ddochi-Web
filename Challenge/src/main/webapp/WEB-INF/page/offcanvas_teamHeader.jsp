<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>🌎 우주최강 2팀 🌎</title>
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,300,700,900"
	rel="stylesheet">
<style>
.SMN_effect-15 a:before, .SMN_effect-15 a:after {
	display: inline-block;
	opacity: 0;
	-webkit-transition: -webkit-transform 0.3s, opacity 0.2s;
	-moz-transition: -moz-transform 0.3s, opacity 0.2s;
	transition: transform 0.3s, opacity 0.2s;
}

.SMN_effect-15 a:before {
	margin-right: 10px;
	content: '[';
	-webkit-transform: translateX(20px);
	-moz-transform: translateX(20px);
	transform: translateX(20px);
}

.SMN_effect-15 a:after {
	margin-left: 10px;
	content: ']';
	-webkit-transform: translateX(-20px);
	-moz-transform: translateX(-20px);
	transform: translateX(-20px);
}

.SMN_effect-15 a:hover:before, .SMN_effect-15 a:hover:after,
	.SMN_effect-15 a:focus:before, .SMN_effect-15 a:focus:after {
	opacity: 1;
	-webkit-transform: translateX(0px);
	-moz-transform: translateX(0px);
	transform: translateX(0px);
}

.menu a {
	color: rgba(0, 0, 0, 0.8);
	font-family: Lato;
	font-size: 17pt;
	font-weight: 400;
	padding: 15px 25px;
	/**/
	position: relative;
	display: block;
	text-decoration: none;
	text-transform: uppercase;
}
</style>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="d-flex justify-content-end">
	<button class="btn btn-danger btn-sm mt-2" type="button"
		data-bs-toggle="offcanvas" data-bs-target="#staticBackdrop"
		aria-controls="staticBackdrop" style="width:152px;">Team Site</button>

	<div class="offcanvas offcanvas-start" data-bs-backdrop="static"
		tabindex="-1" id="staticBackdrop"
		aria-labelledby="staticBackdropLabel">
		<div class="offcanvas-header">
			<h5 class="offcanvas-title fs-2 mt-5" id="staticBackdropLabel">🌎 우주최강 2팀
				</h5>
			<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
				aria-label="Close"></button>
		</div>
		<div class="offcanvas-body">
			<div>
				<div class="d-flex menu expanded text-center SMN_effect-15"><a href="http://192.168.0.70:8080/JSPStudyBBS902/boardMain" class="link-dark text-decoration-none fs-3 mx-5">🦊 양은정</a></div><br>
				<div class="d-flex menu expanded text-center SMN_effect-15"><a href="http://192.168.0.6:8080/miniProject01/management" class="link-dark text-decoration-none fs-3 mx-5">🐻 이현학</a></div><br>
				<div class="d-flex menu expanded text-center SMN_effect-15"><a href="http://192.168.0.17:8080/miniproject/imgList" class="link-dark text-decoration-none fs-3 mx-5">🐹 우황희</a></div><br>
				<div class="d-flex menu expanded text-center SMN_effect-15"><a href="http://192.168.0.9:9000/Challenge/postMain" class="link-dark text-decoration-none fs-3 mx-5">🐭 이형철</a></div><br>
			</div>
		</div>
	</div>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>