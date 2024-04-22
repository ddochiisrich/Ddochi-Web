package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;

@WebServlet("/deleteProcess")
public class BoardDeleteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String sNo = request.getParameter("no");
		String pass = request.getParameter("pass");
		int no = Integer.parseInt(sNo);
		
		BoardDao dao = new BoardDao();
		
		boolean isPassCheck = dao.isPassCheck(no, pass);
		
		if(! isPassCheck) {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append(" alert('비밀번호가 맞지 않습니다.');");
			sb.append(" history.back();");
			sb.append("</script>");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			System.out.println("비밀번호 맞지 않음");
			return;
		}
		dao.deleteBoard(no);
		response.sendRedirect("boardList");
	}

	
}
