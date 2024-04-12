<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로또 당첨 번호 리스트</h2>
	
	<!-- lottoList가 내용이 없거나 null이면  -->
	<c:if test="${ empty lottoList }">
		<div>로또 당첨 번호가 존재하지 않습니다.</div>
	</c:if>
	<!-- lottoList가 내용이 있거나 null이 아니면  -->
	<c:if test="${ not empty lottoList }">
		<ul>
			<c:forEach var="lottoNum" items="${ lottoList }">
				<li>${ lottoNum.times }차 -${ lottoNum.num1 }, ${ lottoNum.num2 }, ${ lottoNum.num3 }, ${ lottoNum.num4 }, ${ lottoNum.num5 }, ${ lottoNum.num6 } <span>+ 보너스 번호 </span>${ lottoNum.bonusNum }</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>