<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 

	request.setCharacterEncoding("UTF-8");

	String name = request.getParameter("name");
	String food = request.getParameter("food");
	String colors = request.getParameter("colors");
	String hamster = request.getParameter("hamster");
	String cat = request.getParameter("cat");
	String tiger = request.getParameter("tiger");
	String lion = request.getParameter("lion");
	String dog = request.getParameter("dog");
	String [] hobby = request.getParameterValues("hobby");	
	
	String str="";
	
	for(int i=0; i<hobby.length; i++){
		if(i == hobby.length - 1){ 
			str += hobby[i];
		} else {
			str += hobby[i] + ", ";
		}
	}

	
	request.setAttribute("name", name);
	request.setAttribute("food", food);
	request.setAttribute("colors", colors);
	request.setAttribute("hamster", hamster);
	request.setAttribute("cat", cat);
	request.setAttribute("tiger", tiger);
	request.setAttribute("lion", lion);
	request.setAttribute("dog", dog);
	request.setAttribute("hobby", str);



	
	pageContext.forward("ch0501ExamResult.jsp");
%>


