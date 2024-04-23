package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.board.vo.Board;

public class BoardDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static DataSource ds;
	
	public BoardDao() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/bbsDBPool");
		} catch (NamingException e) {
			System.out.println("BoardDao() : NamingException");
			e.printStackTrace();
		}
	}
	
	public int getBoardCount(String type, String keyword) {
		System.out.println(type + " - " + keyword);
		String sqlCount = "SELECT COUNT(*) FROM jspbbs WHERE " + type + " LIKE '%' || ? || '%'";
		int count = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}return count;
	}
	
	public ArrayList<Board> searchList(String type, String keyword, int startRow, int endRow){
		
		String sqlSearchList = "SELECT * FROM (SELECT ROWNUM num, no, title, writer, content, reg_date, read_count, pass, file1 FROM (SELECT * FROM jspbbs WHERE " + type + " LIKE ? ORDER BY no DESC)) WHERE num >= ? AND num <= ?";
		ArrayList<Board> boardList = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlSearchList);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				boardList = new ArrayList<Board>();
				Board board = new Board();
				
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("reg_date"));
				board.setReadCount(rs.getInt("read_count"));
				board.setPass(rs.getString("pass"));
				board.setFile1(rs.getString("file1"));
				
				boardList.add(board);
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
		} return boardList;
		
	}
	
	public int getBoardCount() {
		
		String sqlCount = "SELECT count(*) FROM jspbbs";
		int count = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
		
	}
	
	public void deleteBoard(int no) {
		
		String sqlDelete = "DELETE FROM jspbbs WHERE no=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlDelete);
			pstmt.setInt(1, no);
			rs =  pstmt.executeQuery();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateBoard(Board board) {
		
		String fileUpdate = board.getFile1() != null ? ", file1=?" : "";
		String sqlUpdate = "UPDATE jspbbs SET title=?, writer=?, content=?, reg_date=SYSDATE" + fileUpdate + " WHERE no=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlUpdate);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			if(board.getFile1() != null) {
				pstmt.setString(4, board.getFile1());
				pstmt.setInt(5, board.getNo());
			} else {
				pstmt.setInt(4, board.getNo());
			}
			rs =  pstmt.executeQuery();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean isPassCheck(int no, String pass) {
		boolean isPass = false;
		String sqlPass = "SELECT pass FROM jspbbs WHERE no=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlPass);
			pstmt.setInt(1, no);
			rs =  pstmt.executeQuery();
			
			if(rs.next()) {
				isPass = rs.getString(1).equals(pass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isPass;
	}
	
	public ArrayList<Board> boardList(int startRow, int endRow) {
		
		String sqlBoardList = "SELECT * FROM (SELECT ROWNUM as num, sub.* FROM (SELECT * FROM jspbbs ORDER BY no DESC) sub) WHERE num >= ? AND num <= ?";
		ArrayList<Board> boardList = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlBoardList);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<Board>();
			
			while(rs.next()) {
				
				Board b = new Board();
				
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
			System.out.println("BoardDao - boardList() - SQLException");
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
	
	public Board getBoard(int no) {
		String sqlBoard = "SELECT * FROM jspbbs WHERE no=?";
		Board board = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlBoard);
			
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
	
	public void insertBoard(Board board) {
		
		String sqlInsert = "INSERT INTO jspbbs (no, title, writer, content, reg_date, read_count, pass, file1) VALUES (jspbbs_seq.NEXTVAL, ?, ?, ?, SYSDATE, 0, ?, ?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getPass());
			pstmt.setString(5, board.getFile1());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null) pstmt.close();
				if( conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}











