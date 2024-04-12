<%@ page import = "com.jspstudy.ch03_1.vo.Student" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%

Student student1 = new Student("이형철", 29, "남성");
Student student2 = new Student();

student2.setName("이또치");
student2.setAge(26);
student2.setGender("여성");

request.setAttribute("student", student1);

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
