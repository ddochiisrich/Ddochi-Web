package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;
import com.board.vo.Board;

@WebServlet("/updateProcess")
public class BoardUpdateController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String pass, title, writer, content = null;
		int no = 0;
		
		no = Integer.parseInt(request.getParameter("no"));
		pass = request.getParameter("pass");
		
		BoardDao dao = new BoardDao();
		
		boolean isPassCheck = dao.isPassCheck(no, pass);
		
		if(!isPassCheck) {
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
		title = request.getParameter("title");
		writer = request.getParameter("writer");
		content = request.getParameter("content");
		
		
		Board board = new Board();
		
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		board.setNo(no);
		board.setContent(content);
		
		dao.updateBoard(board);
		
		response.sendRedirect("boardList");
		
		
		
	}

	
	
}
