package com.jspstudy.ch06_1.dao;

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

import com.jspstudy.ch06_1.vo.Board;

public class BoardDao {

	private static final String USER = "scott";
	private static final String PASS = "tiger";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin@localhost:1521:xe";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;

	public BoardDao() {

		try {
			Context initContext = new InitialContext();

			Context envContext = (Context) initContext.lookup("java:/comp/env");

			ds = (DataSource) envContext.lookup("jdbc/bbsDBPool");

		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public Board getBoard(int no) {
		String sqlBoard = "select * from jspbbs where no=?";
		Board board = null;


		try {
			// db 연결
			conn = ds.getConnection();
			// sql 쿼리를 발행해주는 객체를 구한다.
			pstmt = conn.prepareStatement(sqlBoard);
			// ?값입력
			pstmt.setInt(1, no);
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
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return board;

	}

	public List<Board> boardList(){
		String sqlBoardList = "SELECT * FROM jspbbs ORDER BY no DESC";
		List<Board> bList = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlBoardList);
			rs = pstmt.executeQuery();

			bList = new ArrayList<>();

			while(rs.next()) {
				Board b = new Board();
				b.setNo(rs.getInt(1));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));
				b.setRegDate(rs.getTimestamp("reg_date"));
				b.setReadCount(rs.getInt("read_count"));

				bList.add(b);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bList;

	} 

} // class end
