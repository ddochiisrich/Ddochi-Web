package com.jspstudy.ch04.servletbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/now")
public class AnnotationNowServlet extends HttpServlet{
	
	// GET 방식 요청처리 - doGet
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h2>에노테이션 서블릿 작성하기</h2>");
		out.println("<div>HELLO Servlet</div>");
		
		out.close();
		
	}
	
	// POST 방식 요청처리 - doPost
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
