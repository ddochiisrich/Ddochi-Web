<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
.phone1, .phone2, .phone3 {
	width: 40px
}

.name {
	width: 166px;
}
</style>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="studentInfo" action="studentInform" method="post">
		<h1>학생 등록 정보</h1>
		<p>
			학&nbsp;생&nbsp;명 : <input type="text" name="name" class="name"
				autofocus>
		</p>
		<p>
			성&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;별 : <input type="radio" name="gender"
				value="male">남 <input type="radio" name="gender"
				value="female">여
		</p>
		<p>
			연&nbsp;락&nbsp;처 : <input type="text" name="phone1" class="phone1">
			- <input type="text" name="phone2" class="phone2"> - <input
				type="text" name="phone3" class="phone3">
		</p>
		<p>희망 취업 분야 :</p>
		<p>
			<input type="checkbox" name="si">SI 업체&nbsp;
			<input type="checkbox" name="sm">SM 업체&nbsp;
			<input type="checkbox" name="solution">솔루션 업체
		</p>
		<p>
			관심분야 : <select name="subject">
				<option value="Android">안드로이드</option>
				<option value="JavaScript">자바스크립트</option>
				<option value="Spring">스프링</option>
				<option value="jQuery">제이쿼리</option>
			</select>
		</p>
	
		 <input type="reset" value="다시쓰기"><input
			type="submit" value="학생등록">
	</form>
</body>
</html>