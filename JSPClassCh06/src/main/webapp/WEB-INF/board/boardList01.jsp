<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.jspstudy.ch06.vo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	body{
		margin:0 auto;
		text-align:center;
	}
	table{
		margin:0 auto;
		text-align:center;
		border: solid 2px black;
		border-collapse:collapse;
	}
	th, td{
		border: solid 1px black;
		padding: 5px 10px;
	}
	
</style>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<H1>🐭 게시글 리스트 🐭</H1>
	<table>
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		
		<!-- 게시글이 있는 경우 -->
		<c:if test="${ not empty bList }">
			<c:forEach var="board" items="${ bList }">
			<tr>
				<td>${ board.no }</td>
				<td>${ board.title }</td>
				<td>${ board.writer }</td>
				<td>${ board.regDate }</td>
				<td>${ board.readCount }</td>
			</tr>
			</c:forEach>
		</c:if>
		<!-- 게시글이 없는 경우 -->
		<c:if test="${ empty bList }">
			<tr>
				<td colspan="5">게시글이 존재하지 않습니다.</td>
			</tr>
		</c:if>
	</table>
</body>
</html>