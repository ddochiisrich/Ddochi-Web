package com.Challenge.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Challenge.dao.ChallengeDao;
import com.Challenge.vo.ChallengeMember;

@WebServlet("/userUpdate")
public class postUserUpdateCheckPassController extends HttpServlet{

	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//세션 추가 부분
		HttpSession session = request.getSession();
	    String loginId = (String) session.getAttribute("loginId");
	    request.setAttribute("loginId", loginId);
	    /////////////////////
	    
	   String enterPass = request.getParameter("userPassCheck");
	   
	   ChallengeDao dao = new ChallengeDao();
	   
	   String sessionPass = dao.passCheck(loginId);

	   boolean result = enterPass.equals(sessionPass);
	   
	   ArrayList<ChallengeMember> memberList = dao.getMemberInfo(loginId);
	   
	   request.setAttribute("memberList", memberList);
	   
	   if(result != true ) { // 비밀번호가 틀릴때
		    response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('please check your password.');");
			out.println(" history.back();");
			out.println("</script>");
			return;
	   } else {
		   RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/post/userUpdate.jsp");
			rd.forward(request, response);
	   }   
		
	}
	
	

}
