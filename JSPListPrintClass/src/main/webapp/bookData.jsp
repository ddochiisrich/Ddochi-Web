<%@ page import = "com.jspstudy.ch03.vo.Book"%>
<%@ page import = "java.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<Book> bookData = new ArrayList<>();

	Book bookset = new Book("./images/javawebprogramming.jpg","프로젝트로 배우는 자바 웹 프로그래밍","황희정","30,000원 --> 24,000원(20%할인) | 포인트 2,400원(10%지급)","오늘 도착예정 | 판매지수 : 1,980");
	bookData.add(bookset);
	
	bookset = new Book("./images/jsp&servlet.jpg","뇌를 자극하는 JSP & Servlet","김윤명","30,000원 --> 27,000원(10%할인) | 포인트 2,700원(10%지급)","내일 도착예정 | 판매지수 : 1,023");
	bookData.add(bookset);
	
	bookset = new Book("./images/headfirstjsp.jpg","Head First Servlet & JSP","케이시 시에라","32,000원 --> 25,600원(20%할인) | 포인트 2,560원(10%지급)","오늘 도착예정 | 판매지수 : 1,011");
	bookData.add(bookset);
	
	bookset = new Book("./images/jsp2.3webprogramming.jpg","최범균의 JSP2.3 웹프로그래밍","최범균","25,000원 --> 22,500원(10%할인) | 포인트 2,250원(10%지급)","모레 도착예정 | 판매지수 : 2,012");
	bookData.add(bookset);
	
	bookset = new Book("./images/jsp&servlet_oracle&eclipse.jpg","백견불여일타 JSP&Servlet : Oracle&Eclipse","성윤정","27,000원 --> 24,300원(10%할인) | 포인트 2,430원(10%지급)","내일 도착예정 | 판매지수 : 1,095");
	bookData.add(bookset);
	
	
	request.setAttribute("bookData", bookData);
	
	// 제어를 다른 jsp 페이지로 이동해서 화면을 구성
 	pageContext.forward("bookList.jsp");
/* 	pageContext.forward("bookListJSTL.jsp"); */
%>