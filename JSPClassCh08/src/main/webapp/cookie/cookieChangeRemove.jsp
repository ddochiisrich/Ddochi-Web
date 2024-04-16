<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie c : cookies){
		String name = c.getName();
		if(name.equals("id")){
			
			Cookie cookie = new Cookie(name,"cookie");
			cookie.setMaxAge(60 * 5);
			cookie.setPath("/JSPClassCh08/cookie/");
			response.addCookie(cookie);
			
		}else if(name.equals("name")){
			
			c.setMaxAge(0);
			response.addCookie(c);
			
		}
;	}
}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	쿠키 변경과 삭제 완료됨<br>
	<a href="cookieView.jsp">/JSPClassCh08/로 쿠키 확인하기</a><br>
	<a href="../cookieInfo/cookieView.jsp">/JSPClassCh08/로 쿠키 확인하기</a>
</body>
</html>