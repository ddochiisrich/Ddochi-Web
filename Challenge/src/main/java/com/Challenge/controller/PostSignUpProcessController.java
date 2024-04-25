package com.Challenge.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Challenge.dao.ChallengeDao;
import com.Challenge.vo.ChallengeMember;

@WebServlet("/signUpProcess")
public class PostSignUpProcessController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("memberId");
		String pw1 = request.getParameter("memberPw1");
		String pw2 = request.getParameter("memberPw2");
		String name = request.getParameter("memberName");
		String nickname = request.getParameter("memberNickname");
		String email = request.getParameter("memberEmail");
		String BeforeemailCheck = request.getParameter("memberMailAgree");
		String address = request.getParameter("memberAddress");
		String phone = request.getParameter("memberPhone");
		
		String emailCheck = BeforeemailCheck == null ? "N" : "Y";
		
		ChallengeDao dao = new ChallengeDao();
		
		ChallengeMember m = new ChallengeMember();
		
		m.setId(id);
		m.setPass(pw1);
		m.setName(name);
		m.setNickName(nickname);
		m.setEmail(email);
		m.setMailCheck(emailCheck);
		m.setAddress(address);
		m.setPhone(phone);
		
		dao.signUp(m);
	
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('Sign Up Success');");
		out.println("window.location.href = 'postMain';");
		out.println("</script>");
	}
}
