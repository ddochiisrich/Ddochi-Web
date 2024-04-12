package com.jspstudy.ch04.formdata;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/formData01")
public class formData01 extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name, id, pass, gender, nMail, aMail, iMail, job;
		
		// 요청데이터 받기
		name = request.getParameter("name");
		id = request.getParameter("id");
		pass = request.getParameter("pass");
		gender = request.getParameter("gender");
		nMail = request.getParameter("nMail");
		aMail = request.getParameter("aMail");
		iMail = request.getParameter("iMail");
		job = request.getParameter("job");
		
		request.setAttribute("name", name);
		request.setAttribute("id", id);
		request.setAttribute("pass", pass);
		request.setAttribute("gender", gender);
		request.setAttribute("nMail", nMail);
		request.setAttribute("aMail", aMail);
		request.setAttribute("iMail", iMail);
		request.setAttribute("job", job);
		
		// 응답 데이터 작성은 직접하지 않고 jsp로 이동해서 작성.
		
		RequestDispatcher rd = request.getRequestDispatcher("view/formDataView01.jsp");
		rd.forward(request, response);
		
		
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		out.println("<h2>사용자가 입력한 값<h2>");
//		out.println("이름 : " + name + "<br>");
//		out.println("성별 : " + gender);
//		
//		out.close();
		
	}
	
}
