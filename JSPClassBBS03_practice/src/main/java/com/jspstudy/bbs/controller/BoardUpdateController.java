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

	private static String uploadDir;
	private static File parentFile;

	@Override
	public void init() {
		uploadDir = getServletContext().getInitParameter("uploadDir");
		String realPath = getServletContext().getRealPath(uploadDir);		
		parentFile = new File(realPath);
		if(! (parentFile.exists() && parentFile.isDirectory())) {
			parentFile.mkdir();
		}
		System.out.println("init - " + parentFile);		
	}
		
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		String contentType = request.getHeader("Content-Type");
		System.out.println("contentType : " + contentType);
			
		String pass= null, title = null, writer = null, content = null;
		String sNo = null, pageNum = null, fileName = null;
		int no = 0;
		
		if(contentType.contains("multipart/form-data")) {
			String realPath = request.getServletContext().getRealPath(uploadDir);
			int maxFileSize = 100 * 1024 * 1024;
			String encoding = "UTF-8"; 

			MultipartRequest multi = new MultipartRequest(request, realPath, 
						maxFileSize, encoding, new DefaultFileRenamePolicy());

			sNo = multi.getParameter("no");
			title = multi.getParameter("title");
			writer = multi.getParameter("writer");
			pass = multi.getParameter("pass");
			content = multi.getParameter("content");	
			pageNum = multi.getParameter("pageNum");	
			
			fileName = multi.getFilesystemName("file1");
			System.out.println("Uploaded file name: " + fileName);
			System.out.println("Original file name: " + multi.getOriginalFileName("file1"));
			
			if(fileName == null) {		
				System.out.println("File not uploaded");		
			}
			
		} else {
			request.setCharacterEncoding("utf-8");
			
			sNo = request.getParameter("no");		
			pass = request.getParameter("pass");
			title = request.getParameter("title");
			writer = request.getParameter("writer");		
			content = request.getParameter("content");
			pageNum = request.getParameter("pageNum");
		}
				
		if(sNo == null || sNo.equals("") || pass == null || pass.equals("")
				|| pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('Invalid access.');");
			out.println("	history.back();");
			out.println("</script>");
			return;
		}
		
		no = Integer.parseInt(sNo);
		
		BoardDao dao = new BoardDao();	
		boolean isPassCheck = dao.isPassCheck(no, pass);
		
		if(! isPassCheck) {			

			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('Incorrect password.');");
			sb.append("	history.back();");
			sb.append("</script>");

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			System.out.println("Password incorrect");
			return;
		} 
		
		Board board = new Board();
		board.setNo(no);
		board.setTitle(title);
		board.setWriter(writer);
		board.setPass(pass);
		board.setContent(content);
		board.setFile1(fileName);
		
		dao.updateBoard(board);	
		
		response.sendRedirect("boardList?pageNum=" + pageNum);
	}
}
