<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpSession session1 = request.getSession(); // 서블릿에서 세션을 이용할때는 이와같이사용
	session.invalidate();
	response.sendRedirect("main.jsp");

%>