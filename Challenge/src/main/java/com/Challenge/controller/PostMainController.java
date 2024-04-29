package com.Challenge.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Challenge.dao.ChallengeDao;
import com.Challenge.vo.ChallengeMember;
import com.Challenge.vo.ChallengePost;



@WebServlet("/postMain")

public class PostMainController extends HttpServlet{

	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//세션 추가 부분
		HttpSession session = request.getSession();
	    String loginId = (String) session.getAttribute("loginId");
	    String nickname = (String) session.getAttribute("nickname");
	    String memberNo = (String) session.getAttribute("memberNo");
	    request.setAttribute("nickname", nickname);
	    request.setAttribute("memberNo", memberNo);
		///////////////
	    
	    String pageNum = request.getParameter("pageNum");
	    String type = request.getParameter("type");
	    String keyword = request.getParameter("keyword");
	    
	    if(pageNum == null) {
	    	pageNum = "1";
	    }
	    
	    int currentPage = Integer.parseInt(pageNum);
	    
	    int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
	    int endRow = startRow + PAGE_SIZE - 1;
	    int listCount = 0;
	    
		ChallengeDao dao = new ChallengeDao();
		listCount = dao.getPostCount();
		
		ArrayList<ChallengePost> cPost = dao.PostList(startRow, endRow);
		
		boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals("")) ? false : true;

				if(! searchOption) {

				listCount = dao.getPostCount();
				cPost = dao.PostList(startRow, endRow);
				
				} else { 
				listCount = dao.getPostCount(type, keyword);
				cPost = dao.searchList(type, keyword, startRow, endRow);
				
				}
				
		
		int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1 - (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		int endPage = startPage + PAGE_GROUP -1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		request.setAttribute("cPost", cPost);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageGroup", PAGE_GROUP);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
		request.setAttribute("keyword", keyword);
		request.setAttribute("type", type);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/post/postList.jsp");
		rd.forward(request, response);	
	}
}
