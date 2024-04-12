<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%!
    private int add(int a, int b){
    	return a + b;
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립팅 요소(Scripting Element) 사용하기</title>
</head>
<body>
	<% 
	int sum = 0;
	for(int i = 0 ; i <= 100 ; i++){
		sum += i;
	}
	%>
	
	1 ~ 100 합 : <%= sum %><br>
	
	<ul>
	
	<%
	for(int i = 0 ; i <= 10 ; i++){
	%>
	<li><%= i %> 번</li>
	<%
	}
	
	sum = 0;
	%>
	</ul>
	sum = <%= sum %><br>
	10 + 20 = <%= add(10, 20) %>
</body>
</html>