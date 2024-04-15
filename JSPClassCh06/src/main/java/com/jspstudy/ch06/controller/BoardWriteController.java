package com.jspstudy.ch06.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.ch06.dao.BoardDao;
import com.jspstudy.ch06.vo.Board;

@WebServlet("/writeProcess")
public class BoardWriteController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 문자셋 처리
		request.setCharacterEncoding("UTF-8");
		
		// 입력처리
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String pass = request.getParameter("pass");
		String content = request.getParameter("content");
		Board b = new Board();
		b.setTitle(title);
		b.setWriter(writer);
		b.setContent(content);
		b.setPass(pass);
		
		// DAO를 이용해서 게시글 정보를 DB에 저장
		BoardDao dao = new BoardDao();
		dao.insertBoard(b);
		
		// 게시글 리스트로 Redirect 시킨다 - 자원이 이동되었다고 응답을 하면서 주소를 알려준다.
		response.sendRedirect("boardList");
		
	}

	
	
}
