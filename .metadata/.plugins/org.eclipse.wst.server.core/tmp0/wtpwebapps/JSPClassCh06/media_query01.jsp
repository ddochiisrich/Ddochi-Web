<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device=width, initial-scale=1">
<title>MEDIA_QUERY</title>
<style>
	.content{
		height: 300px;
		background-color: orange;
	}
	@media screen and (min-width: 768px) {
		.content { background-color: red; }
	}
	@media screen and (min-width: 1024px) {
		.content { background-color: blue; }		
	}
	@media screen and (min-width: 1280px) {
		.content { background-color: green; }		
	}
</style>
</head>
<body>
	<div id="box" class="content"></div>
</body>
</html>