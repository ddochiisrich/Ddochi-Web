package com.jspstudy.bbs.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.service.BoardService;

@WebServlet("/updateProcess")
public class BoardUpdateController extends HttpServlet {

	/* 서블릿 초기화 메서드를 정의하고 애플리케이션 초기화 파라미터인
	 * uploadDir을 읽어서 파일이 업로드 되는 폴더로 사용할 것임 
	 **/
	private static String uploadDir;
	private static File parentFile;

	@Override
	public void init() {
		// web.xml에 지정한 웹 어플리케이션 초기화 파라미터를 읽는다.
		uploadDir = getServletContext().getInitParameter("uploadDir");
		
		/* 웹 어플리케이션 초기화 파라미터에서 읽어온 파일이 저장될 폴더의 
		 * 로컬 경로를 구하여 그 경로와 파일명으로 File 객체를 생성한다.
		 **/
		String realPath = getServletContext().getRealPath(uploadDir);		
		parentFile = new File(realPath);
		
		/* 파일 객체에 지정한 위치에 디렉토리가 존재하지 않거나 
		 * 파일 객체가 디렉토리가 아니라면 디렉토리를 생성한다.
		 **/
		if(! (parentFile.exists() && parentFile.isDirectory())) {
			parentFile.mkdir();
		}
				
		/* ServletContext 객체의 속성으로 저장해 파일 업로드 
		 * 디렉토리 정보를 다른 컴포넌트에서 사용할 수 있도록 하였다.
		 **/ 
		getServletContext().setAttribute("uploadDir", uploadDir);
		getServletContext().setAttribute("parentFile", parentFile);
		System.out.println("init - " + parentFile);
	}
	
	// post 방식의 요청을 처리하는 메소드
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		// BoardService를 이용해 게시 글 수정하기 요청을 처리한다.
		BoardService service = new BoardService();
		String viewPage = service.updateBoard(request, response);
		
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
