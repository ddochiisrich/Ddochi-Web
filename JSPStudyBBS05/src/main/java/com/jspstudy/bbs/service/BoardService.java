package com.jspstudy.bbs.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.dao.BoardDao;
import com.jspstudy.bbs.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardService {
	
	// 한 페이지에 보여 줄 게시 글의 수를 상수로 선언하고 있다.
	private static final int PAGE_SIZE = 10;
	 
	/* 한 페이지에 보여 질 페이지 그룹의 수를 상수로 선언하고 있다.
	 * [이전] 1 2 3 4 5 6 7 8 9 10 [다음]	
	 **/
	private static final int PAGE_GROUP = 10;
	
	// 게시 글 리스트 요청을 받아 DB에서 게시 글 리스트를 읽어와 모델을 만드는 메서드 
	public String boardList(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {		
		
		// 요청 파라미터로 넘어 온 페이지 번호를 읽어온다.
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");	
		String keyword = request.getParameter("keyword");
		
		/* pageNum이 null 이면 처음 게시 글 리스트를 요청하거나 게시 글쓰기에서 
		 * Redirect 되어 넘어온 요청으로 pageNum을 1페이지로 설정한다.
		 * 즉 첫 번째 페이지에 해당하는 게시 글 리스트를 화면에 출력한다. 
		 **/
		if(pageNum == null) {
			pageNum = "1";
		}

		// 요청 파라미터의 pageNum을 int 형으로 변환하여 현재 페이지로 설정
		int currentPage = Integer.parseInt(pageNum);
		
		/* 요청한 페이지에 해당하는 게시 글의 첫 번째 행의 값을 계산한다.
		 * 현재 페이지가 1일 경우 startRow는 1, 2페이지 일 경우 startRow는 11이 된다.	 
		 * 
		 * 테이블에서 현재 페이지에 해당하는 게시 글을 검색할 때 ROWNUM을 사용했다.
		 * ROWNUM은 쿼리의 결과로 검색되는 행들의 순서 값을 가진 의사컬럼으로
		 * 1부터 시작한다. 예를 들어 3페이지에 해당하는 게시 글 리스트를 가져 온다면
		 * 한 페이지에 보여줄 게시 글의 수가 10개로 지정되어 있으므로 startRow는 21이 된다. 
		 * 즉 아래의 공식에 의해 startRow(21) = 3 * 10 - (10 - 1);
		 * 첫 번째 페이지 startRow = 1, 두 번째 페이지 startRow = 11이 된다.
		 **/ 
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		//int startRow = (currentPage - 1) * PAGE_SIZE + 1;
		
		int endRow = startRow + PAGE_SIZE - 1;	
		int listCount = 0;	
		ArrayList<Board> bList = null;	
		
		// BoardDao 객체 생성 
		BoardDao dao = new BoardDao();
		
		
		/* 요청 파라미터에서 type이나 keyword가 비어 있으면 일반 
		 * 게시 글 리스트를 요청하는 것으로 간주하여 false 값을 갖게 한다.
		 **/
		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 
		
		// 검색 요청이 아니면
		if(! searchOption) {
			// 전체 게시 글 수를 구한다.
			listCount = dao.getBoardCount();
			bList = dao.boardList(startRow, endRow);
			
		} else { // 검색 요청이면
			// 검색어에 해당하는 게시 글 수를 구한다.
			listCount = dao.getBoardCount(type, keyword);
			bList = dao.searchList(type, keyword, startRow, endRow);
		}
		System.out.println("listCount : " + listCount);
		
		/* 페이지 그룹 이동 처리를 위해 게시판의 전체 페이지 수를 계산한다.
		 * [이전] 11 12 13...   또는   ... 8 9 10 [다음] 처리
		 * 전체 페이지 = 전체 게시 글 수 / 한 페이지에 표시할 게시 글 수가 되는데 
		 * 이 계산식에서 나머지가 존재하면 전체 페이지 수는 전체 페이지 + 1이 된다.
		 **/
		int pageCount = listCount / PAGE_SIZE 
					+ (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		/* 페이지 그룹 처리를 위해 페이지 그룹별 시작 페이지와 마지막 페이지를 계산하여
		 * 페이지 그룹의 시작 페이지 : 1, 11, 21, 31...
		 * 첫 번째 페이지 그룹에서 페이지 리스트는 1 ~ 10 되므로 currentPage가
		 * 1 ~ 10 사이에 있으면 startPage는 1이 되고 11 ~ 20 사이는 11이 된다. 
		 *
		 * 정수형 연산의 특징을 이용해 startPage를 아래와 같이 구할 수 있다.
		 * 아래 계산식으로 계산된 결과를 보면 현재 그룹의 마지막 페이지일 경우
		 * startPage가 다음 그룹의 시작 페이지가 나오게 되므로 삼항 연산자를
		 * 사용해 현재 페이지가 속한 그룹의 startPage가 되도록 조정 하였다.
		 **/
		 int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
			- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
			
		// 현재 페이지 그룹의 마지막 페이지 : 10, 20, 30...
		int endPage = startPage + PAGE_GROUP - 1;
		
		/* 위의 식에서 endPage를 구하게 되면 endPage는 항상 PAGE_GROUP의
		 * 크기만큼 증가(10, 20, 30 ...) 되므로 맨 마지막 페이지 그룹의 endPage가
		 * 정확하지 못할 경우가 발생하게 된다. 다시 말해 전체 페이지가 53페이지 라고
		 * 가정하면 위의 식에서 계산된 endPage는 60 페이지가 되지만 실제로 
		 * 60페이지는 존재하지 않는 페이지이므로 문제가 발생하게 된다.
		 * 그래서 맨 마지막 페이지에 대한 보정이 필요하여 아래와 같이 endPage와
		 * pageCount를 비교하여 현재 페이지 그룹에서 endPage가 pageCount 보다
		 * 크다면 pageCount를 endPage로 지정 하였다. 즉 현재 페이지 그룹이
		 * 마지막 페이지 그룹이면 endPage는 전체 페이지 수가 되도록 지정한 것이다.
		 **/
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		/* 요청을 처리한 결과 데이터를 HttpServletRequest의 속성에 저장한다.
		 * 
		 * 요청을 처리한 결과 데이터를 모델이라고 부르며 이 데이터는 게시 글 리스트에서
		 * 페이징 처리를 구현하기 위해 필요한 데이터이므로 뷰 페이지로 포워딩 하여
		 * 화면을 구현할 때 사용할 수 있도록 HttpServletRequest의 속성에 저장한다.	
		 **/
		request.setAttribute("bList", bList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageGroup", PAGE_GROUP);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("searchOption", searchOption);
		
		// 검색 요청이면 type과 keyword를 request 영역에 저장한다.
		if(searchOption) {
			request.setAttribute("keyword", keyword);
			request.setAttribute("type", type);	
		}
		
		/* 최종적으로 Redirect 정보와 View 페이지 정보를 문자열로 반환하면 된다.
		 * 
		 * 게시 글 리스트 요청에 대한 결과(모델)를 request 영역의 속성에 저장하고
		 * 요청에 대한 결과(모델)를 출력할 View 페이지와 View 페이지를 호출하는 방식을
		 * 아래와 같이 문자열로 지정하면 된다. 현재 요청을 처리한 후에 Redirect 하려면
		 * 뷰 페이지를 지정하는 문자열 맨 앞에 "r:" 또는 "redirect:"를 접두어로 붙여서
		 * 반환하고 Redirect가 아니라 Forward 하려면 뷰 페이지의 경로만 지정하여 
		 * 문자열로 반환하면 Controller에서 판단하여 Redirect 또는 Forward로 연결된다.
		 **/
		return "/WEB-INF/board/boardList.jsp";
	}
	
	// 게시 글 상세보기 요청을 받아 DB에서 게시 글을 읽어와 모델을 만드는 메서드 
	public String getBoard(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {		
		
		//요청 파라미터로 넘어 온 게시 글 번호와 페이지 번호를 읽어온다.
		String no = request.getParameter("no");	
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");	
		String keyword = request.getParameter("keyword");	

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
			return null;
		}
		
		/* 요청 파라미터에서 type이나 keyword가 비어 있으면 일반 
		 * 게시 글 리스트에서 넘어온 요청으로 간주하여 false 값을 갖게 한다.
		 * 이 정보는 게시 글 리스트와 검색 리스트로 구분해 돌려보내기 위해 필요하다.
		 **/
		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true;
		
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
		request.setAttribute("searchOption", searchOption);
		
		// 검색 요청이면 type과 keyword를 request 영역에 저장한다.
		if(searchOption) {
			request.setAttribute("keyword", keyword);
			request.setAttribute("type", type);	
		}
		
		/* 최종적으로 Redirect 정보와 View 페이지 정보를 문자열로 반환하면 된다.
		 * 
		 * 게시 글 상세보기 요청에 대한 결과(모델)를 request 영역의 속성에 저장하고
		 * 요청에 대한 결과(모델)를 출력할 View 페이지와 View 페이지를 호출하는 방식을
		 * 아래와 같이 문자열로 지정하면 된다. 현재 요청을 처리한 후에 Redirect 하려면
		 * 뷰 페이지를 지정하는 문자열 맨 앞에 "r:" 또는 "redirect:"를 접두어로 붙여서
		 * 반환하고 Redirect가 아니라 Forward 하려면 뷰 페이지의 경로만 지정하여
		 * 문자열로 반환하면 Controller에서 판단하여 Redirect 또는 Forward로 연결된다.
		 **/
		return "/WEB-INF/board/boardDetail.jsp";		
	}
	
	// 게시 글 쓰기 요청을 받아 DB에 게시 글을 추가하는 메서드 
	public String insertBoard(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {		

		/* cos 라이브러리를 이용한 파일 업로드 구현하기 
		 * 
		 * 1. MultipartRequest의 생성자 매개변수에 지정할 데이터를 설정 
		 *
		 * ServletContext 객체의 속성에 저장된 파일을 업로드할
		 * 디렉터리 정보를 읽어와 시스템의 로컬 경로를 구한다. 
		 **/
		String uploadDir = 
				(String) request.getServletContext().getAttribute("uploadDir");
		String realPath = request.getServletContext().getRealPath(uploadDir);		
		
		// 업로드 파일의 최대 크기를 100MB로 지정
		int maxFileSize = 100 * 1024 * 1024;
		
		// 파일의 인코딩 타입을 UTF-8로 지정
		String encoding = "UTF-8"; 
			
		/* 2. 파일 업로드를 처리할 MultipartRequest 객체 생성
		 * 
		 * WEB-INF/lib/cos.jar 파일을 살펴보면 MultipartRequet 클래스는 
		 * com.oreilly.servlet 패키지에 위치하며 파일 업로드를 직접적으로 처리하는
		 * 역할을 담당하는 클래스로 파일 업로드와 관련된 다양한 메소드를 정의하고 있다.
		 * 생성자는 5개로 오버로딩 되어 있고 아래 생성자가 호출되도록 정의되어 있다.
		 *
		 *	public MultipartRequest(HttpServletRequest request,
		 *			String saveDirectory,
		 *			int maxPostSize,
		 *			String encoding,
		 *			FileRenamePolicy policy) throws IOException {...}
		 *
		 * 이 생성자를 살펴보면 request, saveDirectory, maxPostSize는 필수사항으로
		 * 이 매개변수가 null이거나 0보다 작다면 생성자 안에서 예외를 발생시킨다.
		 * 
		 * request : MultipartRequest에 연결할 사용자의 요청 정보가 담긴 객체 
		 * saveDirectory : 업로드 된 파일을 저장할 서버의 디렉터리 지정
		 * maxPostSize : 업로드 파일의 최대 크기 지정
		 * encoding : 파일의 인코딩 방식 지정, 파일 이름이 한글일 경우 필히 utf-8 지정
		 * policy : 사용자가 업로드 한 파일을 저장할 서버의 디렉터리에 현재 업로드 되는
		 *            파일과 이름이 중복된 파일이 존재할 경우 현재 업로드 되는 파일의
		 *            이름을 어떻게 변경할지에 대한 정책을 지정하는 매개변수 이다.
		 *            일반적으로 new DefaultFileRenamePolicy()를 사용하며
		 *            이 클래스는 abc.jpg 파일을 업로드 할때 이미 같은 이름의 파일이 
		 *            존재하면 자동으로 abc1.jpg와 같이 파일을 변경해 준다.
		 *
		 * 아래와 같이 MultipartRequest 객체를 생성하면 saveDirectory에 지정한
		 * 서버의 디렉터리로 파일이 바로 업로드 된다.
		 **/	 
		MultipartRequest multi = new MultipartRequest(request, realPath, 
							maxFileSize, encoding, new DefaultFileRenamePolicy());	
			 
		/* 3. MultipartRequest 객체를 이용해 클라이언트로부터 요청된 데이터를 처리 
		 *
		 * 파일 업로드 처리를 위해서는 모든 요청에 대한 처리를 MultipartRequest 객체를
		 * 이용해 접근해야 한다. 위에서 MultipartRequest 객체를 생성할 때 요청에 대한
		 * 정보를 담고 있는 request를 생성자의 매개변수로 지정해 MultipartRequest를
		 * 통해 사용자의 요청 정보에 접근할 수 있다.
		 *
		 * MultipartRequest 클래스에 정의된 주요 메소드는 아래와 같다.
		 * getParameter(name) : name에 지정한 파라미터 값을 반환
		 * getParameterNames() : 폼에서 전송된 모든 파라미터 이름을 
		 *                                  Enumeration 타입으로 반환  
		 * getParameterValues(name) : name에 지정한 파라미터 값을 String 배열로 반환
		 * getFile(fileName) : 업로드 된 파일 중에서 fileName에 지정한 파라미터
		 *                            이름을 가진 파일의 정보를 File 객체로 반환 
		 * getFileNames() : 폼에서 전송된 모든 파일의 이름을 Enumeration 타입으로 반환
		 * getFileSystemName(name) : name에 지정한 파라미터 이름을 가진
		 *                                         파일의 이름을 반환
		 * getOriginalFileName() : 사용자가 업로드 한 파일의 원본 이름을 반환
		 * getContentType() : 사용자가 업로드 한 파일의 컨텐트 타입을 반환
		 **/
		
		/* 사용자가 폼에 입력한 데이터 처리
		 * MultipartRequest 객체를 통해 파라미터를 읽어 변수에 저장한다. 
		 **/	
		String title = multi.getParameter("title");
		String writer = multi.getParameter("writer");
		String pass = multi.getParameter("pass");
		String content = multi.getParameter("content");		
		
		/* 하나의 게시 글 정보를 저장하는 자바빈 객체를 생성하고 요청 데이터를
		 * Board 객체에 저장한다.
		 **/
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setPass(pass);	
		board.setContent(content);	
			
		/* 사용자가 업로드한 파일 데이터 처리
		 * MultipartRequest 객체를 통해 파일 이름을 구하여 변수에 저장한다.
		 * 파일이 업로드 되지 않으면 fileName은 null 값을 받는다.
		 **/
		String fileName = multi.getFilesystemName("file1");
		System.out.println("업로드 된 파일명 : " + fileName);
		System.out.println("원본 파일명 : " + multi.getOriginalFileName("file1"));
		
		// 파일명이 존재하면 파일명을 지정하고 존재하지 않으면 null로 지정 한다.	 
		board.setFile1(fileName != null ? fileName : null);
		
		if(board.getFile1() == null) {		
			System.out.println("파일이 업로드 되지 않았음");		
		}	
		
		/* BoardDao 객체를 얻어 게시 글을 DB에 추가하기
		 * DB에 게시 글을 추가하고 브라우저에게 게시 글 리스트를 요청하라고 응답
		 * 게시 글쓰기가 완료된 후 Redirect 시키지 않으면 이 페이지를 새로 고침 하여
		 * 재요청 할 때 마다 이미 추가된 게시 글을 계속하여 추가하는 문제가 발생 한다.
		 **/
		BoardDao dao = new BoardDao();
		dao.insertBoard(board);

		/* 최종적으로 Redirect 정보와 View 페이지 정보를 문자열로 반환하면 된다.
		 * 
		 * 게시 글쓰기 요청을 처리하고 Redirect 시키지 않으면 사용자가 브라우저를
		 * 새로 고침 하거나 재요청할 때 마다 이미 DB에 추가된 게시 글을 계속 추가하려는
		 * 동작으로 인해서 중복된 데이터가 저장되거나 또 다른 문제가 발생할 수 있다.
		 * 이런 경우에는 Redirect 기법을 이용해 DB에 추가, 수정, 삭제하는 동작이 아닌
		 * 조회하는 곳으로 이동하도록 하면 문제를 해결 할 수 있다. 
		 * 
		 * 현재 요청을 처리한 후에 Redirect 하려면 뷰 페이지를 지정하는 문자열 맨 앞에
		 * "r:" 또는 "redirect:"를 접두어로 붙여서 반환하고 Redirect가 아니라 Forward
		 * 하려면 뷰 페이지의 경로만 지정하여 문자열로 반환하면 Controller에서 판단하여
		 * Redirect 또는 Forward로 연결된다. 
		 * 
		 * 게시 글쓰기 폼으로 부터 넘어온 신규 게시 글을 DB에 저장한 후 게시 글 리스트
		 * 페이지로 이동시키기 위해 View 페이지 정보를 반환할 때 맨 앞에 "r:" 접두어를
		 * 붙여서 게시 글 리스트 보기 요청을 처리하는 URL를 지정하여 Controller로 넘기면
		 * Controller는 넘겨받은 View 페이지 정보를 분석하여 Redirect 시키게 된다.
		 * 
		 * Redirect는 클라이언트 요청에 대한 결과 페이지가 다른 곳으로 이동되었다고 
		 * 브라우저에게 알려주고 그 이동된 주소로 다시 요청하라고 브라우저에게 URL을
		 * 보내서 브라우저가 그 URL로 다시 응답하도록 처리하는 것으로 아래와 같이
		 * View 페이지 정보의 맨 앞에 "r:" 또는 "redirect:"를 접두어로 붙여서 반환하면
		 * Controller에서 View 페이지 정보를 분석해 Redirect 시키고 이 응답을 받은
		 * 브라우저는 게시 글 리스트를 보여주는 페이지를 다시 요청하게 된다. 
		 **/
		return "r:boardList";
	}
	
	// 게시 글 수정 폼 요청을 받아 DB에서 게시 글을 읽어와 모델을 만드는 메서드 
	public String updateForm(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		String sNo = request.getParameter("no");
		String pass = request.getParameter("pass");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");	
		String keyword = request.getParameter("keyword");
		
		/* 글 번호와 페이지 번호가 파라미터로 전달되지 않는 요청은 정상적인 접근이
		 * 아니므로 자바스크립트를 사용해 경고 창을 띄우고 브라우저에 저장된 
		 * 이전 페이지로 돌려보낸다. Controller로 viewPage 정보를 반환해야 
		 * 하지만 이 경우 viewPage 정보가 없으므로 PrintWriter 객체를
		 * 이용해 클라이언트로 응답할 자바스크립트 코드를 출력하고 null을
		 * 반환하면 Controller에서는 viewPage 정보가 null이 아닐 경우만
		 * 처리하게 되므로 자바스크립트가 브라우저로 전송되어 경고 창이 뜨게 된다.   
		 **/
		if(sNo == null || sNo.equals("") || pass == null || pass.equals("")
			|| pageNum == null || pageNum.equals("")) {

			/* 스트림에 직접 쓰기위해 응답 객체로 부터 스트림을 구한다.
			 * 응답 객체의 스트림을 구하기 전해 ContentType이 설정되어야 한다. 
			 * 그렇지 않으면 한글과 같은 데이터는 깨져서 출력된다.
			 **/			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('정상적인 접근이 아닙니다.');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}
		
		BoardDao dao = new BoardDao();
		int no = Integer.parseInt(sNo);
		boolean isPassCheck = dao.isPassCheck(no, pass);
		
		if(! isPassCheck) {
			/* 스트림에 직접 쓰기위해 응답 객체로 부터 스트림을 구한다.
			 * 응답 객체의 스트림을 구하기 전해 ContentType이 설정되어야 한다. 
			 * 그렇지 않으면 한글과 같은 데이터는 깨져서 출력된다.
			 **/			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('비밀번호가 맞지 않습니다.');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}
		
		/* 요청 파라미터에서 type이나 keyword가 비어 있으면 일반 
		 * 게시 글 리스트에서 넘어온 요청으로 간주하여 false 값을 갖게 한다.
		 * 이 정보는 게시 글 리스트와 검색 리스트로 구분해 돌려보내기 위해 필요하다.
		 **/
		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 	
		
		/* 비밀번호가 맞으면 게시 글 내용을 수정 폼에 출력하기 위해서
		 * BoardDao 객체를 이용해 no에 해당하는 게시 글 정보를 읽어온다.
		 **/	
		Board board = dao.getBoard(Integer.valueOf(no));
		
		// 게시글 정보와 페이지 정보를 Request 속성 영역에 저장 한다.
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);

		// 검색 요청이면 type과 keyword를 request 영역에 저장한다.
		if(searchOption) {			
			request.setAttribute("type", type);			
			request.setAttribute("keyword", keyword);
		}
		
		/* 최종적으로 Redirect 정보와 View 페이지 정보를 문자열로 반환하면 된다.
		 * 
		 * 게시 글 수정 폼 요청에 대한 결과(모델)를 request 영역의 속성에 저장하고
		 * 요청에 대한 결과(모델)를 출력할 View 페이지와 View 페이지를 호출하는 방식을
		 * 아래와 같이 문자열로 지정하면 된다. 현재 요청을 처리한 후에 Redirect 하려면
		 * 뷰 페이지를 지정하는 문자열 맨 앞에 "r:" 또는 "redirect:"를 접두어로 붙여서
		 * 반환하고 Redirect가 아니라 Forward 하려면 뷰 페이지의 경로만 지정하여 
		 * 문자열로 반환하면 Controller에서 판단하여 Redirect 또는 Forward로 연결된다.
		 **/		
		return "/WEB-INF/board/updateForm.jsp";		
	}
	
	// 게시 글 수정 요청을 받아서 DB에서 게시 글을 수정하는 메서드 
	public String updateBoard(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		String contentType = request.getHeader("Content-Type");
		System.out.println("contentType : " + contentType);	

		// 아래 if 문에서 사용할 변수 선언		
		String pass= null, title = null, writer = null, content = null, 
				sNo = null, pageNum = null, fileName = null, type=null, keyword=null;
		int no = 0;

		// 요청이 multipart/form-data 일 경우
		if(contentType.contains("multipart/form-data")) {
			
			/* cos 라이브러리를 이용한 파일 업로드 구현하기 
			 * 1. MultipartRequest의 생성자 매개변수에 지정할 데이터를 설정 
			 **/
			// ServletContext 객체의 속성에 저장된 파일이 저장될 로컬 경로를 구한다.
			String uploadDir = 
					(String) request.getServletContext().getAttribute("uploadDir");
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
			type = multi.getParameter("type");
			keyword = multi.getParameter("keyword");			
				
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
			type = request.getParameter("type");
			keyword = request.getParameter("keyword");	
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
			return null;
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
			return null;
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
		
		/* 요청 파라미터에서 type이나 keyword가 비어 있으면 일반 
		 * 게시 글 리스트에서 넘어온 요청으로 간주하여 false 값을 갖게 한다.
		 * 이 정보는 게시 글 리스트와 검색 리스트로 구분해 돌려보내기 위해 필요하다.
		 **/
		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 	
		
		/* 리다이렉트 할 때 게시 글 리스트의 페이지 번호를 파라미터로 넘겨 사용자가 
		 * 게시 글 수정을 요청한 페이지와 동일한 페이지로 리다이렉트 시킨다.
		 **/
		String url = "boardList?pageNum=" + pageNum;
		
		/* 검색 리스트 상태에서 게시 글 상세보기로 들어와 게시 글을 수정하는 것이라면 
		 * 검색 옵션에 해당하는 검색한 결과에 대한 게시 글 리스트 페이지로 Redirect
		 * 시켜야 하므로 type과 keyword를 Redirect 주소에 추가한다.
		 * Redirect 기법은 요청한 결과가 이동했다고 브라우저에게 이동할 주소를 응답하는
		 * 것으로 브라우저는 주소 표시줄에 주소를 입력해 요청하게 되므로 GET 방식 요청이다. 
		 **/
		if(searchOption) {
			
			/* 리다이렉트 할 때 파라미터에 한글이 포함되어 있으면 한글로 된 파라미터 값은
			 * 공백문자로 변경되어 리다이렉트 되기 때문에 한글 데이터는 깨지게 된다.
			 * 이런 경우에는 java.net 패키지의 URLEncoder 클래스를 이용해 아래와
			 * 같이 수동으로 URL 인코딩을 하면 이 문제를 해결할 수 있다.
			 **/	
			keyword = URLEncoder.encode(keyword, "utf-8");
			url += "&type=" + type + "&keyword=" + keyword;
		}
		System.out.println("keyword : " + keyword);
		System.out.println("url : " + url);
		
		/* 최종적으로 Redirect 정보와 View 페이지 정보를 문자열로 반환하면 된다.
		 * 
		 * 게시 글 수정하기 요청을 처리하고 Redirect 시키지 않으면 사용자가 브라우저를
		 * 새로 고침 하거나 재요청할 때 마다 이미 DB에서 수정된 게시 글을 계속 수정하려는
		 * 동작으로 인해서 문제가 발생할 수 있다. 이런 경우에는 Redirect 기법을 이용해 DB에
		 * 추가, 수정, 삭제가 아닌 조회하는 곳으로 이동하도록 하면 문제를 해결 할 수 있다. 
		 * 
		 * 현재 요청을 처리한 후에 Redirect 하려면 뷰 페이지를 지정하는 문자열 맨 앞에
		 * "r:" 또는 "redirect:"를 접두어로 붙여서 반환하고 Redirect가 아니라 Forward
		 * 하려면 뷰 페이지의 경로만 지정하여 문자열로 반환하면 Controller에서 판단하여
		 * Redirect 또는 Forward로 연결된다. 
		 * 
		 * 게시 글 수정 폼으로 부터 넘어온 게시 글을 DB에서 수정한 후 게시 글 리스트
		 * 페이지로 이동시키기 위해 View 페이지 정보를 반환할 때 맨 앞에 "r:" 접두어를
		 * 붙여서 게시 글 리스트 보기 요청을 처리하는 URL를 지정하여 Controller로 넘기면
		 * Controller는 넘겨받은 View 페이지 정보를 분석하여 Redirect 시키게 된다.
		 * 
		 * Redirect는 클라이언트 요청에 대한 결과 페이지가 다른 곳으로 이동되었다고 
		 * 브라우저에게 알려주고 그 이동된 주소로 다시 요청하라고 브라우저에게 URL을
		 * 보내서 브라우저가 그 URL로 다시 응답하도록 처리하는 것으로 아래와 같이
		 * View 페이지 정보의 맨 앞에 "r:" 또는 "redirect:"를 접두어로 붙여서 반환하면
		 * Controller에서 View 페이지 정보를 분석해 Redirect 시키고 이 응답을 받은
		 * 브라우저는 게시 글 리스트를 보여주는 페이지를 다시 요청하게 된다. 
		 **/
		return "r:" + url;		
	}
	
	// 게시 글 삭제 요청을 받아서 DB에서 게시 글을 삭제하는 메서드 
	public String deleteBoard(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		String sNo = request.getParameter("no");
		String pass = request.getParameter("pass");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		/* 글 번호와 페이지 번호가 파라미터로 전달되지 않는 요청은 정상적인 접근이
		 * 아니므로 자바스크립트를 사용해 경고 창을 띄우고 브라우저에 저장된 
		 * 이전 페이지로 돌려보낸다. Controller로 viewPage 정보를 반환해야 
		 * 하지만 이 경우 viewPage 정보가 없으므로 PrintWriter 객체를
		 * 이용해 클라이언트로 응답할 자바스크립트 코드를 출력하고 null을
		 * 반환하면 Controller에서는 viewPage 정보가 null이 아닐 경우만
		 * 처리하게 되므로 자바스크립트가 브라우저로 전송되어 경고 창이 뜨게 된다.   
		 **/
		if(sNo == null || sNo.equals("") || pass == null || pass.equals("")
			|| pageNum == null || pageNum.equals("")) {

			/* 스트림에 직접 쓰기위해 응답 객체로 부터 스트림을 구한다.
			 * 응답 객체의 스트림을 구하기 전해 ContentType이 설정되어야 한다. 
			 * 그렇지 않으면 한글과 같은 데이터는 깨져서 출력된다.
			 **/			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('정상적인 접근이 아닙니다.');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}
		
		BoardDao dao = new BoardDao();
		int no = Integer.parseInt(sNo);
		boolean isPassCheck = dao.isPassCheck(no, pass);
		
		if(! isPassCheck) {
			/* 스트림에 직접 쓰기위해 응답 객체로 부터 스트림을 구한다.
			 * 응답 객체의 스트림을 구하기 전해 ContentType이 설정되어야 한다. 
			 * 그렇지 않으면 한글과 같은 데이터는 깨져서 출력된다.
			 **/			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('비밀번호가 맞지 않습니다.');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}
		
		// BoardDao 객체를 이용해 게시 글을 삭제한다.
		dao.deleteBoard(no);	
		
		/* 요청 파라미터에서 type이나 keyword가 비어 있으면 일반 
		 * 게시 글 리스트에서 넘어온 요청으로 간주하여 false 값을 갖게 한다.
		 * 이 정보는 게시 글 리스트와 검색 리스트로 구분해 돌려보내기 위해 필요하다.
		 **/
		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 	
		
		/* 리다이렉트 할 때 게시 글 리스트의 페이지 번호를 파라미터로 넘겨 사용자가 
		 * 게시 글 수정을 요청한 페이지와 동일한 페이지로 리다이렉트 시킨다.
		 **/
		String url = "boardList?pageNum=" + pageNum;

		/* 검색 리스트 상태에서 게시 글 상세보기로 들어와 게시 글을 삭제하는 것이라면 
		 * 검색 옵션에 해당하는 검색한 결과에 대한 게시 글 리스트 페이지로 Redirect
		 * 시켜야 하므로 type과 keyword를 Redirect 주소에 추가한다.
		 * Redirect 기법은 요청한 결과가 이동했다고 브라우저에게 이동할 주소를 응답하는
		 * 것으로 브라우저는 주소 표시줄에 주소를 입력해 요청하게 되므로 GET 방식 요청이다. 
		 **/
		if(searchOption) {
			
			/* 리다이렉트 할 때 파라미터에 한글이 포함되어 있으면 한글로 된 파라미터 값은
			 * 공백문자로 변경되어 리다이렉트 되기 때문에 한글 데이터는 깨지게 된다.
			 * 이런 경우에는 java.net 패키지의 URLEncoder 클래스를 이용해 아래와
			 * 같이 수동으로 URL 인코딩을 하면 이 문제를 해결할 수 있다.
			 **/	
			keyword = URLEncoder.encode(keyword, "utf-8");			
			url += "&type=" + type + "&keyword=" + keyword; 
		}

		/* 최종적으로 Redirect 정보와 View 페이지 정보를 문자열로 반환하면 된다.
		 * 
		 * 게시 글 삭제하기 요청을 처리하고 Redirect 시키지 않으면 사용자가 브라우저를
		 * 새로 고침 하거나 재요청할 때 마다 이미 DB에서 삭제된 게시 글을 계속 삭제하려는
		 * 동작으로 인해서 문제가 발생할 수 있다. 이런 경우에는 Redirect 기법을 이용해 DB에
		 * 추가, 수정, 삭제가 아닌 조회하는 곳으로 이동하도록 하면 문제를 해결 할 수 있다. 
		 * 
		 * 현재 요청을 처리한 후에 Redirect 하려면 뷰 페이지를 지정하는 문자열 맨 앞에
		 * "r:" 또는 "redirect:"를 접두어로 붙여서 반환하고 Redirect가 아니라 Forward
		 * 하려면 뷰 페이지의 경로만 지정하여 문자열로 반환하면 Controller에서 판단하여
		 * Redirect 또는 Forward로 연결된다. 
		 * 
		 * 게시 글 삭제하기 요청을 처리한 후에 게시 글 리스트 페이지로 이동시키기 위해 
		 * View 페이지 정보를 반환할 때 맨 앞에 "r:" 접두어를 붙여서 게시 글 리스트 보기
		 * 요청을 처리하는 URL를 지정하여 Controller로 넘기면 Controller는 넘겨 받은
		 * View 페이지 정보를 분석하여 Redirect 시키게 된다.
		 * 
		 * Redirect는 클라이언트 요청에 대한 결과 페이지가 다른 곳으로 이동되었다고 
		 * 브라우저에게 알려주고 그 이동된 주소로 다시 요청하라고 브라우저에게 URL을
		 * 보내서 브라우저가 그 URL로 다시 응답하도록 처리하는 것으로 아래와 같이
		 * View 페이지 정보의 맨 앞에 "r:" 또는 "redirect:"를 접두어로 붙여서 반환하면
		 * Controller에서 View 페이지 정보를 분석해 Redirect 시키고 이 응답을 받은
		 * 브라우저는 게시 글 리스트를 보여주는 페이지를 다시 요청하게 된다. 
		 **/
		return "r:" + url;
	}
}
