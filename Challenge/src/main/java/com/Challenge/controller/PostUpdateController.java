package com.Challenge.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Challenge.dao.ChallengeDao;
import com.Challenge.vo.ChallengePost;

@WebServlet("/updateProcess")
public class PostUpdateController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String title = null;
		String content = null;
		int no = 0;
		
		title = request.getParameter("postTitle");
		content = request.getParameter("postContent");
		no = Integer.parseInt(request.getParameter("updateNo"));
		
		ChallengeDao dao = new ChallengeDao();
		
		ChallengePost post = new ChallengePost();
		post.setPostNo(no);
		post.setPostTitle(title);
		post.setPostContent(content);

		
		dao.postUpdate(post);
		
		response.sendRedirect("postMain");
		
		

		
	}

	
	
}
