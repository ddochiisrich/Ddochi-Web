<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 리스트</title>
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
			<table class="table table-hover">
				<tr class="table-secondary">
					<th>상품명</th>
					<th>가격</th>
					<th>상품코드</th>
					<th>제조사</th>
				</tr>
				<c:if test="${ not empty productList }">
					<c:forEach var="product" items="${ productList }">
						<tr >
							<td><a href="detailProduct?productCode=${ product.productCode }" class="link-underline link-underline-opacity-0 link-dark">${ product.productName }</a></td>
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
		<div class="col text-center"><input type="button" class="btn btn-secondary" value="상품등록" onclick="location.href='addProduct'"></div>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>