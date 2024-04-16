<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="row">
		<div class="col my-5">
			<h1 class="text-center">상품 리스트</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-2"></div>
		<div class="col-8">
			<table class="table">
				<tr class="table-warning">
					<th>상품명</th>
					<th>가격</th>
					<th>상품코드</th>
					<th>제조사</th>
				</tr>
				<c:if test="${ not empty productList }">
					<c:forEach var="product" items="${ productList }">
						<tr>
							<td><a href="detailProduct?productCode=${ product.productCode }">${ product.productCode }</a></td>
							<td>${ product.price }</td>
							<td>${ product.productCode }</td>
							<td>${ product.manufacturer }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${ empty productList }">
				<tr>
					<td colspan="4">등록된 상품이 없습니다.</td>
					</tr>                        
				</c:if>
			</table>
		</div>
		<div class="col-2"></div>
	</div>
	<div class="row">
		<div class="col text-center"><a href="addProduct">상품 등록하기</a></div>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>