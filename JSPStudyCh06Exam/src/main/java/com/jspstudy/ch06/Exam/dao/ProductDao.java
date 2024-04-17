package com.jspstudy.ch06.Exam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.jspstudy.ch06.Exam.vo.Product;

public class ProductDao {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	ArrayList<Product> productList = null;
	
	public Product getProduct(String productCode) {
		
		String sqlBoard = "SELECT * FROM productlist WHERE productCode=?";
		Product product = null;
		
		try {
			// DB 연결
			conn = ds.getConnection();
			// SQL 쿼리를 발행해주는 객체를 구한다.
			pstmt = conn.prepareStatement(sqlBoard);
			// 필요한 값(입력 값, placeholder, ?)
			pstmt.setString(1, productCode);
			// 쿼리를 발행하고 ResultSet 객체를받음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				product = new Product();
				product.setProductName(rs.getString("productName"));
				product.setProductImg(rs.getString("productImg"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setPrice(rs.getString("price"));
				product.setProductCode(rs.getString("productCode"));
				product.setProductcomment(rs.getString("productcomment"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 작업에 사용한 자원을 해제 - 앞에서 가져온 역순으로 닫는다.
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return product;
	}
	
	
	public ProductDao() {

		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/productDBPool");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	
	// 게시글 삭제 메서드
	public void deleteProduct(String productCode) {
		String sqlDelete = "DELETE FROM productlist WHERE productCode=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlDelete);
			
			pstmt.setString(1, productCode);

			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		
	}
	
	// 게시글 수정 메서드
	public void updateProduct(Product product) {
		String sqlUpdate = "UPDATE productlist set productName=?, productImg=?, manufacturer=?, price=?, productCode=?, productcomment=? WHERE productCode=?";
	
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlUpdate);
			
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getProductImg());
			pstmt.setString(3, product.getManufacturer());
			pstmt.setString(4, product.getPrice());
			pstmt.setString(5, product.getProductCode());
			pstmt.setString(6, product.getProductcomment());
			pstmt.setString(7, product.getProductCode());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	

	
	// 게시글 추가 메서드
	public void addProduct(Product product) {
		
		String sqlInsert = "INSERT INTO productlist(productName, productImg, manufacturer, price, productCode, productcomment) " 
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getProductImg());
			pstmt.setString(3, product.getManufacturer());
			pstmt.setString(4, product.getPrice());
			pstmt.setString(5, product.getProductCode());
			pstmt.setString(6, product.getProductcomment());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<Product> productList(){
		String sqlProductList = "SELECT * FROM productlist";
		ArrayList<Product> productList = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlProductList);
			rs = pstmt.executeQuery();
			productList = new ArrayList<Product>();
			
			
			while(rs.next()){
				Product p = new Product();
				p.setProductName(rs.getString("productName"));
				p.setProductImg(rs.getString("productImg"));
				p.setManufacturer(rs.getString("manufacturer"));
				p.setPrice(rs.getString("price"));
				p.setProductCode(rs.getString("productCode"));
				p.setProductcomment(rs.getString("productcomment"));

				productList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productList;
	}
}