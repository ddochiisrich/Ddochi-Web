<%@ page import = "com.jspstudy.ch03_1.vo.Student" %>
<%@ page import = "java.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%

List<Student> studentList = new ArrayList<>();

Student student = new Student("홍길동", 23, "남성");
studentList.add(student);

student = new Student("어머나", 21, "여성");
studentList.add(student);

student = new Student("왕호감", 22, "남성");
studentList.add(student);

student = new Student("왕빛나", 23, "여성");
studentList.add(student);

student = new Student("이나래", 25, "여성");
studentList.add(student);

request.setAttribute("studentList", studentList);

pageContext.forward("studentInfoResult.jsp");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현식과 EL을 이용해 학생정보 출력하기</title>
</head>
<body>

</body>
</html>
