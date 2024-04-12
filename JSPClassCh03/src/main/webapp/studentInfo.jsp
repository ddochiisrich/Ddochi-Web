<%@ page import = "com.jspstudy.ch03.vo.Student" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	Student s1 = new Student("이순신", 23, "남성");
	Student s2 = new Student();
	s2.setName("어머나");
	s2.setAge(33);
	s2.setGender("여성");
	
	request.setAttribute("s1", s1);
	request.setAttribute("s2", s2);
	
	pageContext.forward("studentInfoResult.jsp");
%>