package com.Challenge.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Challenge.dao.ChallengeDao;
import com.Challenge.vo.ChallengePost;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/updateProcess")
public class PostUpdateController extends HttpServlet{

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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String contentType = request.getHeader("Content-Type");
		System.out.println("contentType : " + contentType);

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String title = null;
		String content = null;
		String postFile = null;
		String sNo = null;
		String pageNum = null;
		String fileName = null;
		int no = 0;		

		if(contentType.contains("multipart/form-data")) {

			String realPath = request.getServletContext().getRealPath(uploadDir);

			int maxFileSize = 100 * 1024 * 1024;

			String encoding = "UTF-8";

			MultipartRequest multi = new MultipartRequest(request, realPath,
					maxFileSize, encoding, new DefaultFileRenamePolicy());

			title = multi.getParameter("postTitle");
			content = multi.getParameter("postContent");
			sNo = multi.getParameter("updateNo");
			postFile = multi.getParameter("updatePostFile");
			pageNum = multi.getParameter("updatePageNum");

			fileName = multi.getFilesystemName("updatePostFile");
			System.out.println("업로드 된 파일명 : " + fileName);
			System.out.println("원본 파일명 : " + multi.getOriginalFileName("updatePostFile"));

			if(fileName == null) {
				System.out.println("파일이 업로드 되지 않았음");
			}
		} else {
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");

			title = request.getParameter("postTitle");
			content = request.getParameter("postContent");
			sNo = request.getParameter("updateNo");
			postFile = request.getParameter("updatePostFile");
			pageNum = request.getParameter("updatePageNum");
		}

			System.out.println(sNo);
			System.out.println(pageNum);
		if(sNo == null || sNo.equals("") || pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('잘못된 접근입니다.');");
			out.println(" history.back();");
			out.println("</script>");
			return;
		}

		no = Integer.parseInt(sNo);


		ChallengeDao dao = new ChallengeDao();

		ChallengePost post = new ChallengePost();
		post.setPostNo(no);
		post.setPostTitle(title);
		post.setPostContent(content);
		post.setPostFile(postFile);


		dao.postUpdate(post);

		response.sendRedirect("postMain?pageNum=" + pageNum);

	}



}
