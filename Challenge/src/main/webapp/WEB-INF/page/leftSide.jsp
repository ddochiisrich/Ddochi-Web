<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-3">
		<img alt="" src="./img/profile.jpg"><br>
		<div class="my-3">
			<h3>Manager</h3>
		</div>
		<div>
			<span>There are many variations of passages of Lorem Ipsum
				available, but the majority have suffered alteration in some form,
				by injected humour, or randomised words which don't look even
				slightly believable. If you are going to use a passage of Lorem
				Ipsum, you need to be sure there isn't anything embarrassing hidden
				in the middle of text. </span>
		</div>
		<form name="searchForm" id="searchForm" action="#">
		<div class="input-group my-3">
			<input type="text" class="form-control"
				placeholder="Please enter your keyword"
				aria-label="Recipient's username" aria-describedby="button-addon2" id="keyword">
			<button class="btn btn-outline-secondary" type="button"
				id="button-addon2">Search</button>
		</div>
		</form>
		<hr>
		<div class="my-3">
			Category<br>

			<ul class="list-group list-group-flush my-3">
				<li class="list-group-item"><a href="#">Travel</a></li>
				<li class="list-group-item"><a href="#">Life</a></li>
				<li class="list-group-item"><a href="#">Book</a></li>
				<li class="list-group-item"><a href="#">Hobby</a></li>
				<li class="list-group-item"><a href="#">Car</a></li>
			</ul>
		</div>
	</div>
</body>
</html>