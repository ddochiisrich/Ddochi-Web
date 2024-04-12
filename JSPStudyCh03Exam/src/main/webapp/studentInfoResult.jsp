<%@ page import = "com.jspstudy.ch03_1.vo.Student" %>
<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<%
    List<Student> studentList = (List<Student>) request.getAttribute("studentList");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현식과 EL을 이용해 학생정보 출력하기</title>
</head>
<body>

	<h2>스크립틀릿과 표현식을 이용한 학생 리스트 출력</h2>
	
	
<%
	for(Student student : studentList){
%>    
	<ul>
		<li><%= student.getName() %> (<%=student.getAge()%>) - <%=student.getGender() %></li>
	</ul>		
<%		
	}
%>
	
	
	<h2>JSTL과 EL을 이용한 학생 리스트 출력</h2>
	
	<c:forEach var="studentList" items="${ studentList }">
		<ul>
				<li>${ studentList.name } (${ studentList.age }) - ${ studentList.gender }</li>
		</ul>			
	</c:forEach>
	
</body>
</html>