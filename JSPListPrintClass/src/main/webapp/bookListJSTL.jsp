<%-- 
	EL과 JSTL을 이용해 도서 리스트 출력하기
	table 태그를 사용할 것  
--%>
<%@ page import = "com.jspstudy.ch03.vo.Book" %>
<%@ page import = "java.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	List<Book> bookData = (List<Book>) request.getAttribute("bookData");
%>

<style>

body{
	width:550px;
	margin: 0 auto;
	padding : 10px;
	text-align: center; 
}

img{
	margin-right:15px;
}

table{
	width:550px;
	height:150px;
}

a{
	margin:20px;
}

</style> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL과 JSTL을 이용해 도서 리스트 출력</title>
</head>
<body>

	<h1 align="center">도서 리스트</h1>

	<hr color="skyblue" width="550px">	
	
	<c:forEach var="bookdata" items="${ bookData }">
		<table>
			<tr>
				<th><img src=${ bookdata.img }></th>
				<td><h4>[도서] ${bookdata.title}</h4>
				<p>${ bookdata.author } 저</p>
				<p>${ bookdata.price }</p>
				<p>도착 예상일 : 지금 주문하면 ${ bookdata.delivery }</p></td>
			</tr>
		</table>
		
		<hr>
	</c:forEach>
	
	<a href="bookData.jsp">도서 리스트</a> <a href="lottoNum.jsp">로또 당첨 번호 리스트</a>
</body>
</html>