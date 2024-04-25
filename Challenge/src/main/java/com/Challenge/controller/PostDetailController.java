package com.Challenge.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Challenge.dao.ChallengeDao;
import com.Challenge.vo.ChallengePost;

@WebServlet("/postDetail")
public class PostDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String no = request.getParameter("no");

		
		ChallengeDao dao = new ChallengeDao();
		ChallengePost post = dao.getPost(Integer.valueOf(no));
		
		request.setAttribute("post", post);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/post/postDetail.jsp");
		rd.forward(request, response);
	}

	
	
}
