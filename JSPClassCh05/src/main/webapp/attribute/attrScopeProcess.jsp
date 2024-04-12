<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("UTF-8");
    
    	String id = request.getParameter("id");
    	String name = request.getParameter("name");
    	
    	// 데이터를 저장할 수 있는 속성을 제공하는 4개의 객체
    	pageContext.setAttribute("id", id);
    	
    	// 현재 요청 범위 안에서 유효
    	request.setAttribute("id", id);
    	
    	// 하나의 접속
    	session.setAttribute("id", id);
    	
    	// 하나의 애플리케이션 안에서 유효
    	application.setAttribute("id", id);
    	
    	
    %>
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
	<a href="attrScopeResult.jsp">attrScopeResult</a>
</body>
</html>