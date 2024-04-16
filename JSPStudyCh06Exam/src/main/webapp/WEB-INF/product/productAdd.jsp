<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<h1 class="text-center my-5">상품 등록하기</h1>
		</div>
		<form action="addProcess" id="productAdd" name="productAdd" method="post" enctype="multipart/form-data">
			<div class="row my-3">
			<div class="col-3"></div>
				<div class="col-6">
					상 품 명  <input type="text" id="productName" name="productName" class="form-control"></input>
				</div>
				<div class="col-3"></div>
			</div>

			<div class="row my-3">
			<div class="col-3"></div>
				<div class="col-6">
					판 매 가  <input type="text" id="productPrice" name="productPrice" class="form-control"></input>
				</div>
				<div class="col-3"></div>
			</div>

			<div class="row my-3">
			<div class="col-3"></div>
				<div class="col-6">
					상품코드  <input type="text" id="productCode" name="productCode" class="form-control"></input>
				</div>
				<div class="col-3"></div>
			</div>

			<div class="row my-3">
			<div class="col-3"></div>
				<div class="col-6 auto">
					제  조  사 <select id="productManufacturer" name="productManufacturer" class="form-select form-select-sm">
						<option>삼성전자</option>
						<option>LG전자</option>
						<option>한성컴퓨터</option>
						<option>MSI</option>
					</select>
				</div>
				<div class="col-3"></div>
			</div>

			<div class="row my-3">
			<div class="col-3"></div>
				<div class="col-6">
					상품 상세설명 <br>
					<textarea id="productComment" name="productComment" class="form-control"></textarea>
				</div>
				<div class="col-3"></div>
			</div>

			<div class="row my-3">
			<div class="col-3"></div>
				<div class="col-6">
					상품이미지 <input type="file" id="productImg" name="productImg" class="form-control"></input>
				</div>
				<div class="col-3"></div>
			</div>

			<div class="row my-3">
				<div class="col text-center">
					<input type="reset" id="productReset" value="다시쓰기" class="btn btn-secondary"></input> <input
						type="submit" id="productAdd" value="등록하기" class="btn btn-success"></input> <input
						type="button" id="productCancle" value="취소하기" onclick="location.href='productList'" class="btn btn-danger"></input>
				</div>
			</div>

		</form>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>