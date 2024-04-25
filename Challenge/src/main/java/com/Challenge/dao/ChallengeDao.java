package com.Challenge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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

		String sqlPostList = "SELECT * FROM post ORDER BY post_no DESC";
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
