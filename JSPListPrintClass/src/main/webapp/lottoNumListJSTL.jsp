<%-- 
	EL과 JSTL을 이용해 로또 당첨 번호 리스트 출력
	table 태그를 사용하지 말 것  
--%>
<%@ page import = "com.jspstudy.ch03.vo.LottoNum" %>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
    List<LottoNum> lottotList = (List<LottoNum>) request.getAttribute("lottoList");
%>

<style>
body {
    margin: 20 auto;
    text-align: center; /* 모든 요소를 수평으로 가운데 정렬하기 위해 body의 텍스트 정렬을 가운데로 설정 */
    width:660px;
}

div {
    display: flex; /* 자식 요소들을 가로로 나열 */
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: center; /* 세로 중앙 정렬 */
    border: 2px solid lightgray;
    border-radius: 5px;
    margin-bottom: 1px; /* 각 div 요소 사이의 간격 조정 */
    padding:8px 0px;

}
.no{
	margin: 0px 20px;
	color:blue;
	font-size:22px;
}
.bonus{
	margin: 0px 20px;
	color:grey;
	font-size:15px;
}
img{
	margin:0px 5px;
}

a{
	margin:20px;
}
</style>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL과 JSTL을 이용해 로또 당첨 번호 리스트 출력</title>
</head>
<body>	
	<h1>로또 당첨 번호 리스트</h1>
	<c:forEach var="lottoNum" items="${ lottoList }">
		<div>
		
			<span class="no">${ lottoNum.times }</span>
			<img src="./images/lotto_img/ball_${ lottoNum.num1 }.png">
			<img src="./images/lotto_img/ball_${ lottoNum.num2 }.png">
			<img src="./images/lotto_img/ball_${ lottoNum.num3 }.png">
			<img src="./images/lotto_img/ball_${ lottoNum.num4 }.png">
			<img src="./images/lotto_img/ball_${ lottoNum.num5 }.png">
			<img src="./images/lotto_img/ball_${ lottoNum.num6 }.png">
			<span class="bonus"> + 보너스 번호 </span>  
			<img src="./images/lotto_img/ball_${ lottoNum.bonusNum }.png">
		
		</div><br>				
	</c:forEach>
	<a href="bookData.jsp">도서 리스트</a> <a href="lottoNum.jsp">로또 당첨 번호 리스트</a>

</body>
</html>