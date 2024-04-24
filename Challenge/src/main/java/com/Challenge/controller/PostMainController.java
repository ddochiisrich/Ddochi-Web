package com.Challenge.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Challenge.dao.ChallengeDao;
import com.Challenge.vo.ChallengePost;

@WebServlet("/postMain")
public class PostMainController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ChallengeDao dao = new ChallengeDao();
		ArrayList<ChallengePost> cPost = dao.PostList();
		
		request.setAttribute("cPost", cPost);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/post/postList.jsp");
		rd.forward(request, response);
		
		
	}
	
	
	
}