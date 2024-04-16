package com.jspstudy.ch06.Exam.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.ch06.Exam.dao.ProductDao;

@WebServlet("/deleteProcess")
public class ProductDeleteController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("code");
		
		ProductDao dao = new ProductDao();
		dao.deleteProduct(code);
		response.sendRedirect("productList");
	}

	
	
}
