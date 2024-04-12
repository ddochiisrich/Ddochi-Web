package com.jspstudy.bbs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.service.BoardService;

// 게시 글 리스트 요청을 처리하는 컨트롤러 클래스
@WebServlet("/boardList")
public class BoardListController extends HttpServlet {
	
	// post 방식의 요청을 처리하는 메서드
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		/* 검색 기능의 POST 방식 요청에 따른 문자 셋 처리
		 * 게시 글 리스트에서 검색 버튼이 클릭되면 자바스크립트로 유효성 검사를 한 후
		 * POST 방식으로 요청하게 되는데 검색어에 한글이 포함되어 있을 경우 적절한 
		 * 문자 셋 처리를 하지 않으면 한글이 깨지므로 DB에서 검색이 제대로 되지 못한다.    
		 **/
		request.setCharacterEncoding("utf-8");
		
		/* 이후의 처리는 GET 방식과 POST 방식 요청 처리가 동일하기 때문에
		 * doGet() 메서드를 호출해 이 메서드 안에서 나머지를 처리하도록 했다.  
		 */
		doGet(request, response);
	}
	
	// get 방식의 요청을 처리하는 메소드
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		// BoardService를 이용해 게시 글 리스트 요청 결과 데이터(모델)를 만든다.
		BoardService service = new BoardService();
		String viewPage = service.boardList(request, response);
		
		/* Redirect 정보와 View 페이지의 경로 정보를 저장하는 viewPage가
		 * null이 아니면 Redirect 여부를 판단하여 Redirect라면 Response 객체의
		 * sendRedirect()를 이용해 Redirect 시키고 Redirect가 아니라면
		 * RequestDispatcher를 이용해 View 페이지로 포워딩 시킨다.
		 **/
		if(viewPage != null) {
			
			/* 모델 클래스가 반환한 viewPage에 "redirect" 또는 "r" 접두어가
			 * 존재하면 아래의 viewPage.split(":")[0] 코드에서 "redirect" 또는
			 * "r" 문자열이 반환되고 그렇지 않으면 Forward 할 뷰 페이지의 경로가
			 * 반환되므로 다음과 같이 Redirect와 Forward를 구분하여 처리할 수 있다. 
			 **/  
			String view = viewPage.split(":")[0];
			System.out.println("view : " + view);
			
			if(view.equals("r") || view.equals("redirect")) {
				response.sendRedirect(viewPage.split(":")[1]);
				
			} else {
				RequestDispatcher rd = 
						request.getRequestDispatcher(view);	
				rd.forward(request, response);
			}
		}
	}
}
