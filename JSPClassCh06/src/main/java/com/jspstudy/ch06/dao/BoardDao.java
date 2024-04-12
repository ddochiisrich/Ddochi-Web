package com.jspstudy.ch06.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.jspstudy.ch06.vo.Board;

public class BoardDao {
	
	private static final String USER = "scott";
	private static final String PASS = "tiger";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	//JDBC DB 프로그램에 필요한 객체
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;

	public BoardDao() {
		try {
			// JNDI DBCP 사용하기 위한 기준 객체 생성
			Context initContext = new InitialContext();
			
			// java:/comp/env/이 아래에서 우리가 만든 이름
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			
			ds = (DataSource)envContext.lookup("jdbc/bbsDBPool");
					
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	// NO에 해당하는 게시글을 읽어와 반환하는 메서드
	public Board getBoard(int no) {
		String sqlBoard = "select * from jspbbs where no=?";
		Board board = null;
		

		try {
			// DB 연결
			conn = ds.getConnection();
			// SQL 쿼리를 발행해주는 객체를 구한다.
			pstmt = conn.prepareStatement(sqlBoard);
			// 필요한 값(입력 값, placeholder, ?)
			pstmt.setInt(1, no);
			// 쿼리를 발행하고 ResultSet 객체를받음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("reg_date"));
				board.setReadCount(rs.getInt("read_count"));
				board.setPass(rs.getString("pass"));
				board.setFile1(rs.getString("file1"));
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
		return board;
	}
	
	
	
	
	
	// 하나하나의 기능 - 메서드(함수)
	// DB 테이블에서 게시 글 리스트를 읽어와서 반환하는 메서드
	public List<Board> boardList() {
		String sqlBoardList = "SELECT * FROM jspbbs ORDER BY no DESC";
		List<Board> bList = null;
		
		try {
			
	// 2. DB에 연결
			conn = ds.getConnection();
					
	// 3. DB에 SQL 쿼리를 발행 해주는 객체
			pstmt = conn.prepareStatement(sqlBoardList);
					
	// 4. DB에 쿼리를 발행하고 테이블에서 조회한 결과 집합을 받는다.
			rs = pstmt.executeQuery();
					
			bList = new ArrayList<>();
					
			while(rs.next()){
				// 한 행씩 데이터를 추출해서 Board 객체 담고 List 에 담는다.
				Board b = new Board();
				b.setNo(rs.getInt(1));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("Writer"));
				b.setRegDate(rs.getTimestamp("reg_date"));
				b.setReadCount(rs.getInt("read_count"));
				b.setFile1(rs.getString("file1"));
						
				bList.add(b);
			}

		} catch(SQLException e) {
			e.printStackTrace();
					
		}finally {
			try {
				// DB 작업에 사용한 자원을 해제 - 앞에서 가져온 역순으로 닫는다.
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return bList;
	} // end boardList()
} // end class
	

