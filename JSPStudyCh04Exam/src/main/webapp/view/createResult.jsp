<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
	<h1>회원정보</h1>
	
	이름 : ${ name }<br>
	나이 : ${ age }<br>
	성별 : ${ gender == "male" ? "남성" : gender == "female" ? "여성" : "입력값없음" }<br>
	주소 : ${ address }
</body>
</html>