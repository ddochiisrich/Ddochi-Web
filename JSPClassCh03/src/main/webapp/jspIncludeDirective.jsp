<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.*" %>
<%
	Calendar ca = Calendar.getInstance();    
	Date now = ca.getTime();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>오늘의 메뉴</h2>
	- 명태조림 10,000원<br>
	- 버섯매운탕 10,000원<br>
	- 불고기정식 11,000원<br>
	<%@ include file="includeMenu.jsp" %>
	<b><%= String.format("%1$TY년 %1$Tm월 %1$Td일", now) %></b>
</body>
</html>