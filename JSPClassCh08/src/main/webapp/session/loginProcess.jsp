<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ADMIN_ID" value="admin" scope="application"/>
<c:set var="ADMIN_PASS" value="1234" scope="application"/>

<!-- 로그인 성공이면 isLogin = true, id = admin 세션에 저장하고 main으로 redirect -->
<c:if test="${ param.id == applicationScope.ADMIN_ID && param.pass == applicationScope.ADMIN_PASS}">
	<c:set var="isLogin" value="true" scope="session"/>
	<c:set var="id" value="admin" scope="session"/>
	<c:redirect url="main.jsp"/>
</c:if>

<!-- 로그인 실패하면 알림창 띄우고 로그인 폼으로 돌려보낸다. -->
<c:if test="${ not (param.id == applicationScope.ADMIN_ID && param.pass == applicationScope.ADMIN_PASS)}">
	<script>
		alert("아이디 또는 비밀번호가 틀립니다.");
		history.back();
	</script>
</c:if>