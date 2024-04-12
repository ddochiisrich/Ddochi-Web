<%@ page import = "com.jspstudy.ch03_1.vo.Student" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    Student s = (Student) request.getAttribute("student");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>학생정보 출력하기 - 표현식(Expression)</h2>
	이름 : <%= s.getName() %><br>
	나이 : <%= s.getAge() %><br>
	성별 : <%= s.getGender() %><br><br><br>
	
	<h2>학생정보 출력하기 - 표현 언어(Expression Language)</h2>
	이름 : ${ student.name }<br>
	나이 : ${ student.age }<br>
	성별 : ${ student.gender }
	
</body>
</html>