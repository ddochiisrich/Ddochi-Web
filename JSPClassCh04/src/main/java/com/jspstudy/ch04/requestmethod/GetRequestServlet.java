package com.jspstudy.ch04.requestmethod;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetRequest")
public class GetRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 클라이언트가 입력한 값을 받아서 두 값을 더한 값을 응답으로 만들어서 보낸다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청 데이터 받기
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		
		int number1 = Integer.parseInt(num1);
	    int number2 = Integer.parseInt(num2);
		
		// 응답을 작성 - html 형식
		String method = request.getMethod();
		PrintWriter out = response.getWriter();
		out.println("<h2>" + method + "방식 요청 처리</h2>");
		out.println("첫 번째 입력 값 : " + num1 + "<br>");
		out.println("두 번째 입력 값 : " + num2 + "<br>");
		out.println("두 수를 곱한 값 : " + (number1 * number2) + "<br>");

		out.close();
		
	}

}
