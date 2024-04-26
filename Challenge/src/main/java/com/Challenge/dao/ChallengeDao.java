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
	
	public void postUpdate(ChallengePost post) {
		
		String sqlUpdate = "UPDATE post set post_title=?, post_content=? WHERE post_no=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlUpdate);
			pstmt.setString(1, post.getPostTitle());
			pstmt.setString(2, post.getPostContent());
			pstmt.setInt(3, post.getPostNo());
			
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
						 + "VALUES (post_no.NEXTVAL, 0, ?, ?, 0, 'file', SYSDATE, ?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(1, post.getPostContent());
	        pstmt.setString(2, post.getPostTitle());
	        pstmt.setString(3, memberNo);
	        
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

	public ArrayList<ChallengePost> PostList(){

		String sqlPostList = "SELECT post.*, member.nick_name FROM post INNER JOIN member ON post.post_member_no = member.member_no ORDER BY post.post_no DESC";
		ArrayList<ChallengePost> postList = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlPostList);
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
