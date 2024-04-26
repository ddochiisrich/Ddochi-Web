package com.Challenge.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Challenge.dao.ChallengeDao;
import com.Challenge.vo.ChallengePost;

@WebServlet("/postProcess")
public class PostWriteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("postTitle");
		String content = request.getParameter("postContent");
		
		ChallengePost post = new ChallengePost();
		
		post.setPostTitle(title);
		post.setPostContent(content);
		
		ChallengeDao dao = new ChallengeDao();
		
		dao.insertPost(post, request);
		
		response.sendRedirect("postMain");
	}

	
	
}
