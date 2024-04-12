package com.jspstudy.ch04.requestmethod;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

@WebServlet("/postRequest")
public class PostRequestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 요청 데이터 읽기
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		
		// 입력 값을 가지고 db에 저장, 유효성 검사 등등
		
		// 응답데이터 작성
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<h2>" + req.getMethod() + " 방식 요청 처리</h2>");
		out.println("이름 : " + name + "<br>");
		out.println("주소 : " + address + "<br>");
		
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		doGet(req, resp);
		
	}

		
	
}
