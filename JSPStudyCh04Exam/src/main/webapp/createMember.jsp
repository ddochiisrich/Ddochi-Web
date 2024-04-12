<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style>
.address{
width:300px;
}
</style>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<Form name="createMember" action="createMember" method="post">
		<h1>회원 가입</h1>
		
		이름 : <input type="text" name="name" autofocus><br>
		나이 : <input type="text" name="age"><br>
		성별 : <input type="radio" name="gender" value="male" checked>남성<input type="radio" name="gender" value="female">여성<br>
		주소 : <input type="text" name="address" class="address"><br>
		<input type="reset" value="다시쓰기"><input type="submit" value="전송하기">
	</Form>
</body>
</html>