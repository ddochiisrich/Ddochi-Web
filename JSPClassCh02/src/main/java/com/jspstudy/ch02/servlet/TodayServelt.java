package com.jspstudy.ch02.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿 클래스 - 
@WebServlet("/today")
public class TodayServelt extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object obj;
		
		String id = req.getParameter("id");
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.println("<HTML>");
		out.println("<head><title>현재시간</title><head>");
		out.println("<body>");
		out.println("<h1>입력 값</h1>");
		out.println(" 안녕하세요 "+id+"님~");
		out.println(req.getMethod()+"방식 요청 처리");		
		out.println("</body>");
		out.println("</html>");	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
	}
	
}
