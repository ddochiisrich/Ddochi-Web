package com.jspstudy.bbs.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.dao.BoardDao;
import com.jspstudy.bbs.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		System.out.println("init - " + parentFile);		
	}
	
	// post 방식의 요청을 처리하는 메소드
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		String contentType = request.getHeader("Content-Type");
		System.out.println("contentType : " + contentType);
		
		// 아래 if 문에서 사용할 변수 선언		
		String pass= null, title = null, writer = null, content = null, 
				sNo = null, pageNum = null, fileName = null;
		int no = 0;
		
		/* 비밀번호가 맞지 않을 경우 먼저 처리해야 하나 Content-Type에
		 * 따라서 파라미터를 읽을 수 있는 객체가 다르기 때문에 각각 처리해야 한다.
		 * Content-Type이 application/x-www-form-urlencoded 일 경우
		 * request.getParameter() 메서드로 파라미터를 읽을 수 있지만
		 * multipart/form-data 일 경우에는 request 객체를 이용해
		 * 파라미터를 읽을 수 없기 때문에 각각 처리하는 코드를 기술해야 한다.
		 **/	
		// 요청이 multipart/form-data 일 경우
		if(contentType.contains("multipart/form-data")) {
			
			/* cos 라이브러리를 이용한 파일 업로드 구현하기 
			 * 1. MultipartRequest의 생성자 매개변수에 지정할 데이터를 설정 
			 **/
			// 파일이 저장될 폴더의 로컬 경로를 구한다.
			String realPath = request.getServletContext().getRealPath(uploadDir);
			
			// 업로드 파일의 최대 크기를 100MB로 지정
			int maxFileSize = 100 * 1024 * 1024;
			
			// 파일의 인코딩 타입을 UTF-8로 지정
			String encoding = "UTF-8"; 
				
			/* 2. 파일 업로드를 처리할 MultipartRequest 객체 생성
			 *
			 * 아래와 같이 MultipartRequest 객체를 생성하면 realPath로 지정한
			 * 서버의 디렉토리로 파일이 바로 업로드 된다.
			 * 생성자 인수에 대한 자세한 설명은 게시 글 쓰기 컨트롤러의 주석을 참고하자. 
			 **/	 
			MultipartRequest multi = new MultipartRequest(request, realPath, 
								maxFileSize, encoding, new DefaultFileRenamePolicy());	
			
			/* 3. MultipartRequest 객체를 이용해 클라이언트로부터 요청된 데이터를 처리 
			 *
			 * 파일 업로드 처리를 위해서는 모든 요청에 대한 처리를 MultipartRequest 객체를
			 * 이용해 접근해야 한다. 위에서 MultipartRequest 객체를 생성할 때 요청에 대한
			 * 정보를 담고 있는 request를 생성자의 매개변수로 지정해 MultipartRequest를
			 * 통해 사용자의 요청 정보에 접근할 수 있다.
			 * MultipartRequest 클래스의 주요 메서드는 게시 글 쓰기 컨트롤러의 주석을 참고하자.
			 **/			
			/* 사용자가 폼에 입력한 데이터 읽어오기
			 * MultipartRequest 객체를 통해 파라미터를 읽어 변수에 저장한다. 
			 **/
			sNo = multi.getParameter("no");		
			pass = multi.getParameter("pass");			
			title = multi.getParameter("title");
			writer = multi.getParameter("writer");		
			content = multi.getParameter("content");
			pageNum = multi.getParameter("pageNum");
				
			/* 사용자가 업로드한 파일 데이터 처리
			 * MultipartRequest 객체를 통해 파일 이름을 구하여 변수에 저장한다.
	 		 * 파일이 업로드 되지 않으면 fileName은 null 값을 받는다. 
			 **/
			fileName = multi.getFilesystemName("file1");
			System.out.println("업로드 된 파일명 : " + fileName);
			System.out.println("원본 파일명 : " + multi.getOriginalFileName("file1"));
						
			if(fileName == null) {		
				System.out.println("파일이 업로드 되지 않았음");		
			}
			
		// 요청이 multipart/form-data 아닌 경우	
		} else {		
			// POST 방식의 요청에 대한 문자 셋 처리
			request.setCharacterEncoding("utf-8");
			
			/* 사용자가 폼에 입력한 데이터 읽어오기
			 * HttpServletRequest 객체를 통해 파라미터를 읽어 변수에 저장한다. 
			 **/
			sNo = request.getParameter("no");		
			pass = request.getParameter("pass");			
			title = request.getParameter("title");
			writer = request.getParameter("writer");		
			content = request.getParameter("content");
			pageNum = request.getParameter("pageNum");
		}
		
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
		
		no = Integer.parseInt(sNo);
		
		// BoardDao 객체 생성
		BoardDao dao = new BoardDao();	
		
		// 게시 글의 비밀번호를 체크해 맞지 않으면 이전으로 돌려보낸다.
		boolean isPassCheck = dao.isPassCheck(no, pass);
		if(! isPassCheck) {
			/* 문자열을 보다 효율적으로 다루기 위해서 StringBuilder 객체를 이용해
			 * 응답 데이터를 작성하고 있다. 아래에서는 비밀번호가 틀리면 사용자에게
			 * 경고 창을 띄우고 브라우저의 history 객체를 이용해 바로 이전으로 
			 * 돌려보내기 위해서 자바스크립트로 응답을 작성하고 있다.
			 **/
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('비밀번호가 맞지 않습니다.');");
			sb.append("	history.back();");
			sb.append("</script>");
			
			/* 응답 객체에 연결된 PrintWriter 객체를 이용해 응답 데이터를 전송하고
			 * 더 이상 실행할 필요가 없으므로 return 문을 이용해 현재 메서드를 종료한다.
			 **/
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());			
			return;
		}
		
		/* 하나의 게시 글 정보를 저장하는 VO 객체를 생성하고
		 * 요청 파라미터로 넘겨받은 데이터를 Board 객체에 저장한다.
		 **/
		Board board = new Board();
		board.setNo(no);
		board.setTitle(title);
		board.setWriter(writer);
		board.setPass(pass);
		board.setContent(content);	
		board.setFile1(fileName);
		
		// BoardDao 객체를 이용해 게시 글을 수정한다.
		dao.updateBoard(board);	

		/* 게시 글 수정이 완료된 후 response 내장객체의 sendRedirect() 메서드를
		 * 이용해 게시 글 리스트로 Redirect 시킨다. response 내장객체의 sendRedirect()
		 * 메서드는 요청한 자원이 다른 곳으로 이동되었다고 웹브라우저에게 응답하면서
		 * 이동할 URL을 알려주고 그 쪽으로 다시 요청하라고 응답하는 메소드이다.
		 * 웹 브라우저가 요청한 컨텐츠가 다른 곳으로 이동되었다고 응답하면서 그 쪽으로
		 * 다시 요청하라고 이동할 주소를 웹브라우저에게 알려주면 웹브라우저는 그 주소로
		 * 다시 요청하게 되는데 이를 리다이렉션이라고 한다.
		 * 
		 * 게시 글 수정이 완료된 후 Redirect 시키지 않으면 이 페이지를 새로 고침 하여
		 * 재요청 할 때 마다 이미 수정된 게시 글을 계속해서 수정하려고 하는 문제가 발생한다.
		 * 
		 * 리다이렉트 할 때 페이지 번호를 요청 파라미터로 넘겨줘야 사용자가 이전에 있었던
		 * 게시 글 리스트 페이지로 이동할 수 있다.
		 **/		
		response.sendRedirect("boardList?pageNum=" + pageNum);
	}
}
