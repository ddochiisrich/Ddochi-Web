package com.Challenge.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Challenge.dao.ChallengeDao;
import com.Challenge.vo.ChallengePost;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/postProcess")
public class PostWriteController extends HttpServlet {

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
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String realPath = request.getServletContext().getRealPath(uploadDir);
		int maxFileSize = 100 * 1024 * 1024;
		String encoding = "UTF-8";		

		MultipartRequest multi = new MultipartRequest(request, realPath,
				maxFileSize, encoding, new DefaultFileRenamePolicy());

		request.setCharacterEncoding("UTF-8");

		String title = multi.getParameter("postTitle");
		String content = multi.getParameter("postContent");

		ChallengePost post = new ChallengePost();

		post.setPostTitle(title);
		post.setPostContent(content);

		String fileName = multi.getFilesystemName("file1");
		System.out.println("업로드 된 파일명 : " + fileName);
		System.out.println("원본 파일명 : " + multi.getOriginalFileName("file1"));

		post.setPostFile(fileName);

		if(post.getPostFile() == null) {
			System.out.println("Not Found File");
		}

		ChallengeDao dao = new ChallengeDao();

		dao.insertPost(post, request);

		response.sendRedirect("postMain");
	}



}
