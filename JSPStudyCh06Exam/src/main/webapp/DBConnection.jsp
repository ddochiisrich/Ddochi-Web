<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*, com.jspstudy.ch06.Exam.vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String user = "scott";
	String pass = "tiger";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	Class.forName(driver);
	
	Connection conn = DriverManager.getConnection (url, user, pass);
	
	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM productlist");
	
	ResultSet rs = pstmt.executeQuery();
	
	ArrayList<Product> productList = new ArrayList<>();
	
	while(rs.next()){
		Product p = new Product();
		p.setProductName(rs.getString("productName"));
		p.setProductImg(rs.getString("productImg"));
		p.setManufacturer(rs.getString("manufacturer"));
		p.setPrice(rs.getInt("price"));
		p.setProductCode(rs.getString("productCode"));
		p.setproductcomment(rs.getString("productcomment"));
		
		productList.add(p);
	}
	
	if(rs != null) rs.close();
	if(pstmt != null) pstmt.close();
	if(conn != null) conn.close();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품설명</title>
</head>
<body>
	<h1>상품 리스트</h1>

	<table>
		<tr>
			<th>상품명</th>
			<th>가격</th>
			<th>상품코드</th>
			<th>제조사</th>
		</tr>
		<c:forEach var="product" items="<%= productList %>">
			<tr>
				<td>${ product.productName }</td>
				<td>${ product.productImg }</td>
				<td>${ product.manufacturer }</td>
				<td>${ product.price }</td>
				<td>${ product.productCode }</td>
				<td>${ product.productcomment }</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="#">상품 등록하기</a>
</body>
</html>