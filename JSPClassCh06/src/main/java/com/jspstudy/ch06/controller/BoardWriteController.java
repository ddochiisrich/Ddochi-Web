package com.jspstudy.ch06.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.ch06.dao.BoardDao;
import com.jspstudy.ch06.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/writeProcess")
public class BoardWriteController extends HttpServlet{
	
	private static String uploadDir;
	private static File parentFile;
	
	// 서블릿 초기화 메서드
	@Override
	public void init() {
		
		uploadDir = getServletContext().getInitParameter("uploadDir");
		String realPath = getServletContext().getRealPath(uploadDir);
		parentFile = new File(realPath);
		
		if(!(parentFile.exists() && parentFile.isDirectory())) {
			parentFile.mkdir();
		}
		System.out.println("init() - " + parentFile);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// COS 라이브러리의 객체 생성자에 제공할 데이터
		// 실제 업로드된 파일이 저장될 폴더
		String realPath = request.getServletContext().getRealPath(uploadDir);
		
		// 업로드 파일의 최대크기
		// 1024
		int maxFileSize = 100*1024*1024;
		
		// 파일의 인코딩
		String encoding = "UTF-8";
		
		// 파일 업로드를 처리할 cos 라이브러리의 객체
		MultipartRequest multi = new MultipartRequest(request, realPath, maxFileSize, encoding, new DefaultFileRenamePolicy());
		
		
		
		// 입력처리
		String title = multi.getParameter("title");
		String writer = multi.getParameter("writer");
		String pass = multi.getParameter("pass");
		String content = multi.getParameter("content");
		
		Board b = new Board();
		b.setTitle(title);
		b.setWriter(writer);
		b.setContent(content);
		b.setPass(pass);
		
		// 사용자가 업로드한 파일정보에 접근
		String fileName = multi.getFilesystemName("file1");
		System.out.println("업로드된 파일명 : " +  fileName);
		System.out.println("원본 파일 명 : " + multi.getOriginalFileName("file1"));
		
		b.setFile1(fileName);
		
		if(b.getFile1() == null) {
			System.out.println("파일이 업로드 되지않음.");
		}
		
		// DAO를 이용해서 게시글 정보를 DB에 저장
		BoardDao dao = new BoardDao();
		dao.insertBoard(b);
		
		// 게시글 리스트로 Redirect 시킨다 - 자원이 이동되었다고 응답을 하면서 주소를 알려준다.
		response.sendRedirect("boardList");
		
	}

	
	
}
