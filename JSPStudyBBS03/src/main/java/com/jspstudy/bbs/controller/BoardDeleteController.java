package com.jspstudy.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.dao.BoardDao;

@WebServlet("/deleteProcess")
public class BoardDeleteController extends HttpServlet {

	// post 방식의 요청을 처리하는 메소드
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		//POST 요청 방식의 문자 셋 처리
		request.setCharacterEncoding("utf-8");

		String sNo = request.getParameter("no");
		String pass = request.getParameter("pass");
		String pageNum = request.getParameter("pageNum");
		
		/* no 또는 pageNum이 비어 있으면 비정상적인 요청이므로 경고 창을
		 * 띄우고 브라우저의 history 객체를 이용해 바로 이전으로 돌려보내기 위해서
		 * 자바스크립트로 응답을 작성해 클라이언트로 보낸다. 
		 **/
		if(sNo == null || sNo.equals("") || pass == null || pass.equals("")
			|| pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();	
			out.println("<script>");
			out.println("	alert('잘못된 접근입니다.');");
			out.println("	history.back();");
			out.println("</script>");
			return;
		}	
		
		// BoardDao 객체 생성
		BoardDao dao = new BoardDao();
		int no = Integer.parseInt(sNo);
		
		// 게시 글의 비밀번호를 체크해 맞지 않으면 이전으로 돌려보낸다.
		boolean isPassCheck = dao.isPassCheck(no, pass);	
		if(! isPassCheck) {
			System.out.println("비밀번호 맞지 않음");
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('비밀번호가 맞지 않습니다.');");
			sb.append("	history.back();");
			sb.append("</script>");
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());			
			return;
		}
		
		// BoardDao 객체를 이용해 게시 글을 삭제한다.
		dao.deleteBoard(no);

		/* 게시 글 삭제가 완료된 후 response 내장객체의 sendRedirect() 메서드를
		 * 이용해 게시 글 리스트로 Redirect 시킨다. response 내장객체의 sendRedirect()
		 * 메서드는 요청한 자원이 다른 곳으로 이동되었다고 웹브라우저에게 응답하면서
		 * 이동할 URL을 알려주고 그 쪽으로 다시 요청하라고 응답하는 메소드이다.
		 * 웹 브라우저가 요청한 컨텐츠가 다른 곳으로 이동되었다고 응답하면서 그 쪽으로
		 * 다시 요청하라고 이동할 주소를 웹브라우저에게 알려주면 웹브라우저는 그 주소로
		 * 다시 요청하게 되는데 이를 리다이렉션이라고 한다.
		 * 
		 * 게시 글 삭제가 완료된 후 Redirect 시키지 않으면 이 페이지를 새로 고침 하여
		 * 재요청 할 때 마다 이미 삭제된 게시 글을 계속해서 삭제하려고 하는 문제가 발생한다.
		 * 
		 *	리다이렉트 할 때 페이지 번호를 요청 파라미터로 넘겨줘야 사용자가 이전에 있었던
		 * 게시 글 리스트 페이지로 이동할 수 있다. 
		 **/
		response.sendRedirect("boardList?pageNum=" + pageNum);
	}
}
