<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사용자가 입력한 값</h2>
	이름 : ${ name }<br>
	아이디 : ${ id }<br>
	비밀번호 : ${ pass }<br>
	성별 : ${ gender == "male" ? "남성" : gender == "female" ? "여성" : "선택되지않음" }<br>
	공지메일 : ${ nMail == "on" ? "공지메일 받음" : "공지메일 받지않음" }<br>
	광고메일 : ${ aMail == "on" ? "광고메일 받음" : "광고메일 받지않음"  }<br>
	정보메일 : ${ iMail == "on" ? "정보메일 받음" : "정보메일 받지않음"  }<br>
	직업 : ${ job }<br>
	
</body>
</html>