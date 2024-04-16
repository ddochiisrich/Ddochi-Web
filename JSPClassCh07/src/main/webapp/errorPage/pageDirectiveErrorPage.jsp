<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 

	이 JSP 페이지가 실행되다가 에러가 발생하면 처리할 JSP 페이지를 지정할 수 있음.
    
-->
<%@ page errorPage="pageDirectiveIsErrorPage.jsp" %>
<%

	String str = null;
	System.out.println(str.equals("ERROR"));
	
%>