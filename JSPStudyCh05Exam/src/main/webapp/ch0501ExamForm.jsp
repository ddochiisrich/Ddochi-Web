<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선호도 테스트</title>
</head>
<body>
	<form action="ch0501ExamProcess.jsp" method="post">
		<h1>선호도 테스트</h1>
		<p>이름 : <input type="text" name="name"></p>
		<p>좋아하는 색: <input type="radio" name="colors" value="빨강색">빨강색 
					 <input type="radio" name="colors" value="초록색">초록색 
					 <input type="radio" name="colors" value="파란색">파란색 </p>
		<p>좋아하는 음식 : <select name="food">
							<option value="짜장면">짜장면</option>
							<option value="짬뽕">짬뽕</option>
							<option value="볶음밥">볶음밥</option>
							<option value="탕수육">탕수육</option>
					   </select></p>			 
		<p>좋아하는 동물 (모두 고르세요) : </p>
			<input type="checkbox" name="hamster" value="햄스터">햄스터 		 
			<input type="checkbox" name="cat" value="고양이">고양이 	
			<input type="checkbox" name="tiger" value="호랑이">호랑이 	
			<input type="checkbox" name="lion" value="사자">사자 	
			<input type="checkbox" name="dog" value="개">개 	
		
		<p>취미 (모두 고르세요) : </p>
				<select name="hobby" multiple>
					<option name="game" value="게임">게임</option>
					<option name="travel" value="여행">여행</option>
					<option name="read" value="독서">독서</option>
					<option name="fishing" value="낚시">낚시</option>
				</select><br>
		
		<input type="reset" value="다시쓰기"> <input type="submit" value="전송하기">
	</form>
</body>
</html>