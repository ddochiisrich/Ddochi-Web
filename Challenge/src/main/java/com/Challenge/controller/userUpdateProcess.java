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

@WebServlet("/userUpdateProcess")
public class userUpdateProcess extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
	    String loginId = (String) session.getAttribute("loginId");
	    request.setAttribute("loginId", loginId);
		
		String pass = request.getParameter("memberPw1");
		String name = request.getParameter("memberName");
		String nick = request.getParameter("memberNickname");
		String email = request.getParameter("memberEmail");
		String address = request.getParameter("memberAddress");
		String phone = request.getParameter("memberPhone");
		
		ChallengeDao dao = new ChallengeDao();
		
		ChallengeMember m = new ChallengeMember();
		
		m.setPass(pass);
		m.setName(name);
		m.setNickName(nick);
		m.setEmail(email);
		m.setAddress(address);
		m.setPhone(phone);
		m.setId(loginId);
		
		dao.memberUpdate(m);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('user Information edit Success');");
		out.println("window.location.href = 'postMain';");
		out.println("</script>");
		
	}

	
	
}
