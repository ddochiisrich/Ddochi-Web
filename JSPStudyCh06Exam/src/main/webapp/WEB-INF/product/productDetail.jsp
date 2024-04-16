<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.7.1.min.js"></script>
<script src="js/formCheck.js"></script>
</head>
<body>
	<div class="container text-center">
		<form name="checkForm" id="checkForm">
			<input type="hidden" name="code" id="code" value="${ product.productCode }">
			<input type="hidden" name="pdName" id="pdName" value="${ product.productName }">
		</form>
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
						<td><img src="upload/${ product.productImg }"
							style="height: 300px;"></img></td>

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
			<div class="row">
				<div class="col-2"></div>
				<div class="col-8 justify-content-center">
					<input type="button" value="상품등록" id="add" name="add" onclick="location.href='addProduct'" class="btn btn-secondary mx-3"/>
					<input type="button" value="상품수정" id="edit" name="edit" class="btn btn-warning my-3"/>
					<input type="button" value="상품삭제" id="delete" name="delete" class="btn btn-danger mx-3"/>
					<input type="button" value="상품목록" id="list" name="list" onclick="location.href='productList'" class="btn btn-secondary my-3"/>
				</div>
				<div class="col-2"></div>
			</div>
		</div>
		<script src="bootstrap/bootstrap.bundle.min.js"></script>
	</div>
</body>
</html>