package com.jspstudy.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.dao.BoardDao;
import com.jspstudy.bbs.vo.Board;

@WebServlet("/boardDetail")
public class BoardDetailController extends HttpServlet {

	// get 방식의 요청을 처리하는 메소드
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		//요청 파라미터로 넘어 온 게시 글 번호와 페이지 번호를 읽어온다.
		String no = request.getParameter("no");	
		String pageNum = request.getParameter("pageNum");

		/* no 또는 pageNum이 비어 있으면 비정상적인 요청이므로 경고 창을
		 * 띄우고 브라우저의 history 객체를 이용해 바로 이전으로 돌려보내기 위해서
		 * 자바스크립트로 응답을 작성해 클라이언트로 보낸다. 
		 **/
		if(no == null || no.equals("") || pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근입니다.');");
			out.println("	history.back();");
			out.println("</script>");
			return;
		}
		
		// BoardDao 객체 구하고 게시 글 번호(no)에 해당하는 게시 글을 읽어온다.
		BoardDao dao = new BoardDao();
		Board board = dao.getBoard(Integer.valueOf(no));

		/* 요청을 처리한 결과 데이터를 HttpServletRequest의 속성에 저장한다.
		 * 게시 글 정보와 함께 페이징 처리에 필요한 데이터도 같이 저장해야 상세보기
		 * 페이지 화면을 작성할 때 사용자가 이전에 있었던 게시 글 리스트 페이지로
		 * 되돌아갈 수 있도록 구현할 수 있다. 
		 **/
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		
		/* view 페이지로 제어를 이동해 요청에 대한 결과를 출력하기 위해
		 * HttpServletRequest 객체로 부터 RequestDispatcher 객체를 구하고
		 * /WEB-INF/board/boardDetail.jsp 페이지로 포워딩 한다. 
		 **/
		RequestDispatcher rd = 
				request.getRequestDispatcher("/WEB-INF/board/boardDetail.jsp");
		rd.forward(request, response);
	}
}
