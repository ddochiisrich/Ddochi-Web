<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container text-center">
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6 my-5">
				<h1>상품 상세 정보</h1>
			</div>
			<div class="col-3"></div>
		</div>

		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<table class="table table-bordered">
					<tr>
						<td>${ product.productName }</td>
					<tr>
				</table>
			</div>
			<div class="col-3"></div>
		</div>

		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<table class="table table-bordered">
					<tr>
						<td><img
							src="upload/${ product.productImg }" style="width: 500px;"></img></td>

					</tr>
				</table>
			</div>
			<div class="col-3"></div>
		</div>
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<table class="table table-bordered">
					<tr>
						<td>제조사 : ${ product.manufacturer }<br> <br> 판매가 :
							${ product.price }<br> <br> 상품코드 : ${ product.productCode }<br>
							<br> ${ product.productcomment }
						</td>
					</tr>
				</table>
			</div>
			<div class="col-3"></div>
			<div class="row text-center">
				<div class="col-2"></div>
				<div class="col-8">
					<a href="#">상품등록하기</a>
					<a href="#">상품수정하기</a><br>
					<a href="#">상품삭제하기</a>
					<a href="productList">상품리스트보기</a>
				</div>
				<div class="col-2"></div>
			</div>
		</div>
		<script src="bootstrap/bootstrap.bundle.min.js"></script>
	</div>
</body>
</html>