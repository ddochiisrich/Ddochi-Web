<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table {
		border: 1px solid blue;
		border-collapse: collapse; 
	}
	td { border: 1px solid blue; height: 30px; }
	.title {
		width: 100px;
		padding-left: 5px;
	}
	.content {
		width: 250px;
		padding-left: 5px;
	}
</style>
</head>
<body>	
	<table>
		<tr><td colspan="2" style="text-align: center; 
				height: 30px; line-height: 30px">
				<h3>회원 등록 완료</h3>
				아래와 같이 회원 등록이 완료 되었습니다.<br/></td></tr>
		<tr><td  class="title">이 름</td>
			<td class="content"></td></tr>
		<tr><td class="title">아이디</td>
			<td class="content"></td></tr>
		<tr><td class="title">비밀번호</td>
			<td class="content"></td></tr>
		<tr><td class="title">전화번호</td>
			<td class="content"></td></tr>
		<tr><td class="title">성 별</td>
			<td class="content">				
			</td></tr>
		<tr><td class="title">나 이</td>
			<td class="content"></td></tr>
		<tr><td class="title">관심분야</td>
			<td class="content">}</td></tr>
		<tr><td rowspan="3" class="title">메일수신</td>
			<td class="content">				
			</td></tr>
		<tr><td class="content">			
			</td></tr>
		<tr><td class="content">			
		</td></tr>
		<tr><td class="title">직 업</td>
			<td class="content"></td></tr>
	</table>	 
</body>
</html>