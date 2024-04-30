<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.blinking{
  -webkit-animation:blink 2s ease-in-out infinite alternate;
  -moz-animation:blink 2s ease-in-out infinite alternate;
  animation:blink 2s ease-in-out infinite alternate;
}
@-webkit-keyframes blink{
  0% {opacity:0;}
  100% {opacity:1;}
}
@-moz-keyframes blink{
  0% {opacity:0;}
  100% {opacity:1;}
}
@keyframes blink{
  0% {opacity:0;}
  100% {opacity:1;}
}
</style>
</head>
<body>
	<div class="row my-1">
		<div class="col-6">Total : ${sessionScope.totalCount}</div>


		<!-- 로그인이 안되었을 때 -->
		<c:if test="${ empty nickname }">
		<div class="col-6 d-flex justify-content-end">
			<button type="button" class="btn btn-danger btn-sm"
				style="width: 70px;" onclick="location.href='signUp'">Sign up</button>
			&nbsp;&nbsp;
			<button type="button" class="btn btn-danger btn-sm"
				style="width: 70px;" onclick="location.href='login'">Login</button>
		</div>
		</c:if>
		
		
		
		<!-- 로그인 상태일 때 -->
		<c:if test="${ not empty nickname }">
		<div class="col-6 d-flex justify-content-end">
		<span style="margin-top:4px;" class="image blinking">Welcome ${ nickname }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-danger btn-sm"
				style="width: 70px;" onclick="location.href='passCheck'">Profile</button>
			&nbsp;&nbsp;
			<button type="button" class="btn btn-danger btn-sm"
				style="width: 70px;" onclick="location.href='logoutProcess'">Logout</button>
		</div>
		</c:if>
		

		<div class="row my-5 text-center">
			<div class="col">
				<h1><a href="http://localhost:9000/Challenge/postMain"><img src="./img/logo.png" height="150px"></a></h1>
			</div>
		</div>

		
	</div>
</body>
</html>