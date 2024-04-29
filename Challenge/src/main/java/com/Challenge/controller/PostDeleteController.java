package com.Challenge.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Challenge.dao.ChallengeDao;

@WebServlet("/deleteProcess")
public class PostDeleteController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String sNo = request.getParameter("detailPostNo");
		String pageNum = request.getParameter("detailPostPageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		

		if(sNo == null || sNo.equals("") || pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('잘못된 접근입니다.');");
			out.println(" history.back();");
			out.println("</script>");
			return;
		}

		int no = Integer.parseInt(sNo);

		ChallengeDao dao = new ChallengeDao();

		dao.postDelete(no);
		
		boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		
		String url = "boardList?pageNum=" + pageNum;
		
		if(searchOption) {
			keyword = URLEncoder.encode(keyword, "UTF-8");
			url +="&type=" + type + "&keyword=" + keyword;
		}

		response.sendRedirect(url);

	}



}
