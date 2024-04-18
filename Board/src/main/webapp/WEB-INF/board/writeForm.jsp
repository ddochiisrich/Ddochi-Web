<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>Write Form</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.7.1.min.js"></script>
<script src="js/formcheck.js"></script>
</head>
<body>
	<div class="container">
		<!-- header -->
		<%@ include file="../pages/header.jsp"%>
		<!-- content -->
		<div class="row my-5" id="global-content">
			<div class="col">
				<div class="row text-center">
					<div class="col">
						<h2 class="fs-3 fw-bold">Write Form</h2>
					</div>
				</div>
				<form name="writeForm" action="writeProcess" id="writeForm"
					class="row g-3 border-primary" method="post">
					<div class="col-4 offset-md-2">
						<label for="writer" class="form-label">Writer</label> <input
							type="text" class="form-control" name="writer" id="writer"
							placeholder="please write the writer">
					</div>
					<div class="col-4 ">
						<label for="pass" class="form-label">Password</label> <input
							type="password" class="form-control" name="pass" id="pass">
					</div>
					<div class="col-8 offset-md-2">
						<label for="title" class="form-label">Title</label> <input
							type="text" class="form-control" name="title" id="title">
					</div>
					<div class="col-8 offset-md-2">
						<label for="content" class="form-label">Content</label>
						<textarea class="form-control" name="content" id="content"
							rows="10"></textarea>
					</div>
					<div class="col-8 offset-md-2 text-center mt-5">
						<input type="submit" value="Post" class="btn btn-primary" />
						&nbsp;&nbsp;<input type="button" value="List"
							onclick="location.href='boardList'" class="btn btn-primary" />
					</div>
				</form>
			</div>
		</div>
		<!-- footer -->
		<%@ include file="../pages/footer.jsp"%>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>