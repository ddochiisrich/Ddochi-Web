<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	page : ${ pageScope.id }<br>
	request : ${ requestScope.id }<br>
	session : ${ sessionScope.id }<br>
	application : ${ applicationScope.id }<br>
	<a href="#">새로고침</a>
</body>
</html>