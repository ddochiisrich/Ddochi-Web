<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%
 // 스크립틀릿 - 자바코드를 작성하는 곳
 Calendar today = Calendar.getInstance();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	오늘은 <%= today.get(Calendar.YEAR) %> 년
	<%= today.get(Calendar.MONTH)+1 %> 월
	<%= today.get(Calendar.DAY_OF_MONTH) %> 일 입니다.<br>
	
	<h2>입력 폼</h2>
	<form action="today" method="get">
		아이디 : <input type="text" name="id">
		<input type="submit" value="전송하기">
		<button type="submit">전송하기</button>
	</form>
	
</body>
</html>