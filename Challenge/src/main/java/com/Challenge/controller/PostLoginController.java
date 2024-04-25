package com.Challenge.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Challenge.dao.ChallengeDao;
import com.Challenge.vo.ChallengeMember;

@WebServlet("/loginProcess")
public class PostLoginController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		String loginId = request.getParameter("memberLoginId");
		String loginPw = request.getParameter("memberLoginPw1");
		
		ChallengeDao dao = new ChallengeDao();
		boolean loginCheck = dao.accountCheck(loginId, loginPw);
		
		String nickname = dao.getNickname(loginId, loginPw);
		
		if(!loginCheck) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('check your Id or Password!')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return;
		}
		
		response.sendRedirect("postMain");		
	}	
}
