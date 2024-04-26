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

@WebServlet("/updateForm")
public class PostUpdateFormController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String sNo = request.getParameter("detailPostNo");
		
		int no = Integer.parseInt(sNo);
		
		ChallengeDao dao = new ChallengeDao();
		
		ChallengePost post = dao.getPost(no);
		
		request.setAttribute("post", post);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/post/postUpdate.jsp");
		rd.forward(request, response);
		
	}

	
	
}
