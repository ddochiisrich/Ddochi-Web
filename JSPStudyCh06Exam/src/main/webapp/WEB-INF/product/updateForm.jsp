<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.jspstudy.ch06.Exam.vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.7.1.min.js"></script>
<script src="js/formCheck.js"></script>
</head>
<body>
	<div class="container">
		
		<div>
			<h1 class="text-center my-5">상품 수정하기</h1>
		</div>
		<form id="updateForm" name="updateForm" action="updateProcess"
			method="post" enctype="multipart/form-data">
			<input type="hidden" id="updateCode" name="updateCode" value="${ product.productCode }"/>
			<div class="row my-3">
				<div class="col-3"></div>
				<div class="col-6">
					상 품 명<input type="text" id="updateName" name="updateName"
						value="${ product.productName }" class="form-control">
				</div>
				<div class="col-3"></div>
			</div>

			<div class="row my-3">
				<div class="col-3"></div>
				<div class="col-6">
					판 매 가<input type="text" id="updatePrice" name="updatePrice"
						value="${ product.price }" class="form-control">
				</div>
				<div class="col-3"></div>
			</div>

			<div class="row my-3">
				<div class="col-3"></div>
				<div class="col-6">
					제 조 사<select id="updateManufacturer" name="updateManufacturer"
						class="form-select form-select-sm">
						<option value="삼성전자"
							${product.manufacturer == '삼성전자' ? 'selected' : ''}>삼성전자</option>
						<option value="LG전자"
							${product.manufacturer == 'LG전자' ? 'selected' : ''}>LG전자</option>
						<option value="한성컴퓨터"
							${product.manufacturer == '한성컴퓨터' ? 'selected' : ''}>한성컴퓨터</option>
						<option value="MSI"
							${product.manufacturer == 'MSI' ? 'selected' : ''}>MSI</option>
					</select>
				</div>
				<div class="col-3"></div>
			</div>

			<div class="row my-3">
				<div class="col-3"></div>
				<div class="col-6">
					상품 상세설명<br>
					<textarea id="updateComment" name="updateComment"
						class="form-control">${ product.productcomment }</textarea>
				</div>
				<div class="col-3"></div>
			</div>

			<div class="row my-3">
				<div class="col-3"></div>
				<div class="col-6">
					상품이미지<input type="file" id="updateImg" name="updateImg"
						class="form-control"></input>
				</div>
				<div class="col-3"></div>
			</div>


			<div class="row my-3">
				<div class="col text-center">
					<input type="reset" id="updateReset" value="다시쓰기"
						class="btn btn-secondary"></input> <input type="submit"
						id="updateEdit" value="수정하기" class="btn btn-success"></input> <input
						type="button" id="updateCancle" value="취소하기"
						onclick="location.href='productList'" class="btn btn-danger"></input>
				</div>
			</div>

		</form>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>