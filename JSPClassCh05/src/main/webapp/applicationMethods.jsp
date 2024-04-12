<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>applicationMethods</title>
</head>
<body>
	<h2>웹 서버 정보</h2>
	<ul>
		<li>웹 서버 종류 : <%= application.getServerInfo() %></li>
		<li>서블릿 버전 : <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %></li>
	</ul>
	<h2>웹 애플리케이션 정보</h2>
	<ul>
		<li>웹 애플리케이션 컨텍스트 패스 : <%= application.getContextPath() %></li>
		<li>웹 애플리케이션 파일의 절대경로 : <%= application.getRealPath("applicationMethods.jsp") %></li>
		<li>웹 애플리케이션 이름 : <%= application.getServletContextName() %></li>
	</ul>
</body>
</html>