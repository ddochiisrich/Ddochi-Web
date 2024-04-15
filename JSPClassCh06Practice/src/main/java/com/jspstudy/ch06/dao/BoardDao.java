package com.jspstudy.ch06.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.jspstudy.ch06.vo.Board;

public class BoardDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static DataSource ds;

	public BoardDao() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");

			ds = (DataSource) envContext.lookup("jdbc/bbsDBPool");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Board> boardList(){
		String sqlBoardList = "SELECT * FROM jspbbs ORDER BY no DESC";
		ArrayList<Board> boardList = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlBoardList);
			rs = pstmt.executeQuery();

			boardList = new ArrayList<Board>();

			while(rs.next()) {
				Board b= new Board();

				b.setNo(rs.getInt("no"));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));
				b.setContent(rs.getString("content"));
				b.setRegDate(rs.getTimestamp("reg_date"));
				b.setReadCount(rs.getInt("read_count"));
				b.setPass(rs.getString("pass"));
				b.setFile1(rs.getString("file1"));

				boardList.add(b);
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
		return boardList;
	}
}
