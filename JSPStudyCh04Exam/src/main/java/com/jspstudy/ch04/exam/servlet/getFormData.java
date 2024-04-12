package com.jspstudy.ch04.exam.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/plusForm")
public class getFormData extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		
		int firstNum = Integer.parseInt(num1);
		int secondNum = Integer.parseInt(num2);
		
		int sum = 0;
		
		for(int i = firstNum ; i <= secondNum ; i++) {
			sum += i;
		}
		
		
		request.setAttribute("firstNum", firstNum);
		request.setAttribute("secondNum", secondNum);
		request.setAttribute("sum", sum);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/plusForm.jsp");
		rd.forward(request, response);
		
		
		
	}
	
}
