<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.7.1.min.js"></script>
<script src="js/formCheck.js"></script>
</head>
<body>
	<div class="col-3">
		<img alt="" src="./img/profile.jpg"><br>
		<div class="my-3">
			<h3>🐚 Lorem</h3>
		</div>
		<div>
			<span>There are many variations of passages of Lorem Ipsum
				available, but the majority have suffered alteration in some form,
				by injected humour, or randomised words which don't look even
				slightly believable. If you are going to use a passage of Lorem
				Ipsum, you need to be sure there isn't anything embarrassing hidden
				in the middle of text. </span>
		</div>
		<hr>
		<form name="searchForm" id="searchForm">
			<div class="row input-group my-3">
				<div class="col-3">
					<select name="type" id="type" class="form-control text-danger" style="font-size:12px;">
						<option value="post_title">제목</option>
						<option value="nick_name">글쓴이</option>
					</select>
				</div>
				<div class="col-9" style="padding:0px;">
				<input type="text" class="form-control"
					placeholder="Please enter your keyword"
					aria-label="Recipient's username" aria-describedby="button-addon2"
					id="keyword" name="keyword" style="font-size:12px;">
					</div>
			</div>
			<div class="row">
				<div class="col-12">
					<button class="btn btn-outline-danger" type="submit"
						id="button-addon2" style="width : 294px">Search</button>
				</div>
			</div>
		</form>
		<hr>
		<div class="my-3">
			<span class="fs-4">Category</span><br>

			<ul class="list-group list-group-flush my-3">
				<li class="list-group-item"><a href="#" class="link-dark text-decoration-none text-danger">Travel</a></li>
				<li class="list-group-item"><a href="#" class="link-dark text-decoration-none text-danger">Life</a></li>
				<li class="list-group-item"><a href="#" class="link-dark text-decoration-none text-danger">Book</a></li>
				<li class="list-group-item"><a href="#" class="link-dark text-decoration-none text-danger">Hobby</a></li>
				<li class="list-group-item"><a href="#" class="link-dark text-decoration-none text-danger">Car</a></li>
			</ul>
		</div>
	</div>
</body>
</html>