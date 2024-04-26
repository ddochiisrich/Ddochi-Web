package com.Challenge.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/postForm")
public class PostWriteFormController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String memberNo = (String) session.getAttribute("memberNo");
		
		if(memberNo == null) {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('please Login!!')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return;		
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/post/postForm.jsp");
		rd.forward(request, response);
		
	}
	
}
