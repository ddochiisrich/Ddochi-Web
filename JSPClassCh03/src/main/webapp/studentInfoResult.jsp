<%@ page import = "com.jspstudy.ch03.vo.Student" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	Student s1 = (Student) request.getAttribute("s1");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>학생 정보 - 표현식(Expression)</h2>
	이름 : <%= s1.getName() %><br>
	나이 : <%= s1.getAge() %><br>
	<%-- 성별 : <%= s1.getGender() %> --%>
	
	<h2>학생 정보 - 표현언어(Expression Language)</h2>
	이름 : ${ s2.name }<br>
	나이 : ${ s2.age }<br>
	성별 : ${ s2.gender } 
</body>
</html>