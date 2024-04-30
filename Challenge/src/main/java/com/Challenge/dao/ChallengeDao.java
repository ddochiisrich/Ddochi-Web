package com.Challenge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.Challenge.vo.*;

public class ChallengeDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;

	public ChallengeDao() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/challengeDBPool");
		} catch (NamingException e) {
			e.printStackTrace();
		}	
	}

	public int getVisitTotalCount() {
		String sqlVisitTotalCount = "select COUNT(*) AS total from visit";
		int totalCount = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlVisitTotalCount);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
	            totalCount = rs.getInt("total");
	        }
			
			System.out.println(totalCount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			} catch(SQLException se) {}
		}
		return totalCount;
	}
	
	public void setVisitTotalCount() { // 전체 방문자 수 증가
		String sqlVisitTotalCount = "INSERT INTO VISIT (V_DATE) VALUES (sysdate)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlVisitTotalCount);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			} catch(SQLException se) {}
		}
		
	}
	
	public void plusView(int no) {
		
		String sqlPlusView = "UPDATE post SET view1 = view1 + 1 WHERE post_no = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlPlusView);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			} catch(SQLException se) {}
		}
		
	}
	
	public ArrayList<ChallengePost> searchList(
			String type, String keyword, int startRow, int endRow) {
		
		String sqlSearchList = "SELECT p.*, m.nick_name FROM (SELECT ROWNUM num, post_no, post_title, post_content, post_file, like1, post_reg_date, view1, post_member_no FROM (SELECT * FROM post WHERE " + type + " LIKE ? ORDER BY post_no DESC)) p JOIN member m ON p.post_member_no = m.member_no WHERE p.num >= ? AND p.num <= ?";
				
		String sqlSearchListNickname = "SELECT p.*, m.nick_name FROM (SELECT ROWNUM num, post_no, post_title, post_content, post_file, like1, post_reg_date, view1, post_member_no FROM ( SELECT *  FROM post  WHERE post_member_no IN (SELECT member_no FROM member WHERE " + type + " LIKE ?) ORDER BY post_no DESC)) p JOIN member m ON p.post_member_no = m.member_no WHERE p.num >= ? AND p.num <= ?";
		ArrayList<ChallengePost> postList = null;
		try{
			conn = ds.getConnection();
			if(type.equals("nick_name")) {
				pstmt = conn.prepareStatement(sqlSearchListNickname);
			}else if(type.equals("post_title")) {
			pstmt = conn.prepareStatement(sqlSearchList);
			}
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			postList = new ArrayList<ChallengePost>();
			
			while(rs.next()) {
				ChallengePost p = new ChallengePost();

				p.setPostNo(rs.getInt("post_no"));
				p.setPostTitle(rs.getString("post_title"));
				p.setPostContent(rs.getString("post_content"));
				p.setPostFile(rs.getString("post_file"));
				p.setLike1(rs.getInt("like1"));
				p.setPostRegDate(rs.getTimestamp("post_reg_date"));
				p.setPostView(rs.getInt("view1"));
				p.setWriter(rs.getString("nick_name"));

				postList.add(p);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			} catch(SQLException se) {}
		}
		return postList;
	}

	public int getPostCount(String type, String keyword) {
		System.out.println(type + " - " + keyword);

		String sqlCount = "SELECT COUNT(*) FROM post WHERE "
				+ type + " LIKE '%' || ? || '%'";

		String sqlCountNickname = "SELECT COUNT(*) "
				+ "FROM post "
				+ "WHERE post_member_no IN ("
				+ "    SELECT member_no "
				+ "    FROM member"
				+ "    WHERE " + type + " LIKE '%' || ? || '%'"
				+ ")";
		
		int count = 0;
		try{
			conn = ds.getConnection();
			if(!type.equals("nick_name")) {
				pstmt = conn.prepareStatement(sqlCount);
				}
			if(type.equals("nick_name")) {
				pstmt = conn.prepareStatement(sqlCountNickname);
				}
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException se) {}
		}
		return count;
	}


	public int getPostCount() {
		String sqlCount = "SELECT COUNT(*) FROM post";
		int count = 0;

		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException se) {}
		}
		return count;
	}

	public void postDelete(int no) {
		String sqlDelete = "DELETE FROM post WHERE post_no=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlDelete);
			pstmt.setInt(1, no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void postUpdate(ChallengePost post) {

		String fileUpdate = post.getPostFile() != null ? ", post_file=?" : "";
		String sqlUpdate = "UPDATE post SET post_title=?, post_content=?" + fileUpdate + " WHERE post_no=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlUpdate);
			pstmt.setString(1, post.getPostTitle());
			pstmt.setString(2, post.getPostContent());

			if(post.getPostFile() != null) {
				pstmt.setString(3, post.getPostFile());
				pstmt.setInt(4, post.getPostNo());
			} else {
				pstmt.setInt(3, post.getPostNo());
			}

			pstmt.executeUpdate();

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


	}

	public boolean memberCheck(String no, HttpServletRequest request) {

		boolean memberCheck = false;

		HttpSession session = request.getSession();
		String memberNo = (String) session.getAttribute("memberNo");

		String sqlMemberCheck = "SELECT * FROM post WHERE post_no=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlMemberCheck);
			pstmt.setString(1, no);

			rs = pstmt.executeQuery();

			String postMemberNo = null;
			if(rs.next()) {

				postMemberNo = (String) rs.getString("post_member_no");

			}
			memberCheck = postMemberNo.equals(memberNo) ? true : false;


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
		return memberCheck;

	}

	public void insertPost(ChallengePost post, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String memberNo = (String) session.getAttribute("memberNo");

		String sqlInsert = "INSERT INTO post (post_no, view1, post_content, post_title, like1, post_file, post_reg_date, post_member_no) " 
				+ "VALUES (post_no.NEXTVAL, 0, ?, ?, 0, ?, SYSDATE, ?)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(1, post.getPostContent());
			pstmt.setString(2, post.getPostTitle());
			pstmt.setString(3, post.getPostFile());
			pstmt.setString(4, memberNo);


			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean accountCheck(String id, String pass) {

		boolean loginCheck = false;
		String sqlLogin = "SELECT * FROM member WHERE id=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlLogin);

			pstmt.setString(1, id);


			rs = pstmt.executeQuery();

			while(rs.next()) {

				String memberId = rs.getString("id");
				String memberPassword = rs.getString("password");

				if(memberId.equals(id) && memberPassword.equals(pass)) {
					loginCheck = true;
					break;
				}

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
		return loginCheck;
	}

	public String getNickname(String id, String pass) {

		String nickname = null;
		String sqlLogin = "SELECT * FROM member WHERE id=? and password=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlLogin);

			pstmt.setString(1, id);
			pstmt.setString(2, pass);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				nickname = rs.getString("nick_name");			
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
		return nickname;
	}

	public String getMemberNo(String id, String pass) {

		String memberNo = null;
		String sqlLogin = "SELECT * FROM member WHERE id=? and password=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlLogin);

			pstmt.setString(1, id);
			pstmt.setString(2, pass);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				memberNo = rs.getString("member_no");			
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
		return memberNo;
	}

	public void signUp(ChallengeMember member){

		String sqlSignUp = "INSERT INTO member (member_no, nick_name, name, password, id, email, address, phone, mail_check) "
				+ "VALUES (member_no_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";



		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlSignUp);
			pstmt.setString(1, member.getNickName());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPass());
			pstmt.setString(4, member.getId());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getMailCheck());

			pstmt.executeUpdate();
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
	}

	public ChallengePost getPost(int no) {

		String sqlPost = "SELECT * FROM post WHERE post_no=?";
		ChallengePost post = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlPost);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				post = new ChallengePost();

				post.setPostNo(rs.getInt("post_no"));
				post.setPostTitle(rs.getString("post_title"));
				post.setPostContent(rs.getString("post_content"));
				post.setPostFile(rs.getString("post_file"));
				post.setLike1(rs.getInt("like1"));
				post.setPostRegDate(rs.getTimestamp("post_reg_date"));
				post.setPostView(rs.getInt("view1"));

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
		return post;
	}

	public ArrayList<ChallengePost> PostList(int startRow, int endRow){

		String sqlPostList = "SELECT p.*, m.nick_name FROM (SELECT ROWNUM num, post_no, post_title, post_content, post_file, like1, post_reg_date, view1, post_member_no FROM (SELECT * FROM post ORDER BY post_no DESC)) p JOIN member m ON p.post_member_no = m.member_no WHERE p.num >= ? AND p.num <= ?";


		ArrayList<ChallengePost> postList = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlPostList);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			postList = new ArrayList<ChallengePost>();

			while(rs.next()) {
				ChallengePost p = new ChallengePost();

				p.setPostNo(rs.getInt("post_no"));
				p.setPostTitle(rs.getString("post_title"));
				p.setPostContent(rs.getString("post_content"));
				p.setPostFile(rs.getString("post_file"));
				p.setLike1(rs.getInt("like1"));
				p.setPostRegDate(rs.getTimestamp("post_reg_date"));
				p.setPostView(rs.getInt("view1"));
				p.setWriter(rs.getString("nick_name"));

				postList.add(p);
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
		return postList;
	}
}
