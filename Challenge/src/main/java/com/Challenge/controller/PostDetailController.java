package com.Challenge.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");

		if(no == null || no.equals("") || pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('The wrong approach');");
			out.println(" history.back();");
			out.println("</script>");
			return;
		}
		
		boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		
		ChallengeDao dao = new ChallengeDao();
		ChallengePost post = dao.getPost(Integer.valueOf(no));
		
		boolean check = dao.memberCheck(no, request);
		
		request.setAttribute("post", post);
		request.setAttribute("check", check);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
			request.setAttribute("keyword", keyword);
			request.setAttribute("type", type);
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/post/postDetail.jsp");
		rd.forward(request, response);
	}

	
	
}
