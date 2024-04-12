package com.jspstudy.bbs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.service.BoardService;

@WebServlet("/deleteProcess")
public class BoardDeleteController extends HttpServlet {

	// post 방식의 요청을 처리하는 메소드
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		//POST 요청 방식의 문자 셋 처리
		request.setCharacterEncoding("utf-8");
		
		// BoardService를 이용해 게시 글 삭제하기 요청을 처리한다.
		BoardService service = new BoardService();
		String viewPage = service.deleteBoard(request, response);
		
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
