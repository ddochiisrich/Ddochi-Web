<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 등록 정보</title>
</head>
<body>
	<h1>학생 등록 정보</h1>
	이름 : ${ name }<br>
	성별 : ${ gender }<br>
	연락처 : ${ phone1 } - ${ phone2 } - ${ phone3 }<br>
	희망 취업 분야 : ${ si == "on" ? "si" : null } ${ sm == "on" ? "sm" : null } ${ solution == "on" ? "solution " : null }<br>
	관심 분야 : ${ subject } <br>
	
</body>
</html>