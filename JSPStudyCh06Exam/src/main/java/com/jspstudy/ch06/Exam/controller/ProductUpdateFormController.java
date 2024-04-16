package com.jspstudy.ch06.Exam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.ch06.Exam.dao.ProductDao;
import com.jspstudy.ch06.Exam.vo.Product;

@WebServlet("/updateForm")
public class ProductUpdateFormController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTR-8");
		
		
		String pCode = request.getParameter("productCode");
		
		ProductDao dao = new ProductDao();
		
		Product product = dao.getProduct(pCode); 
		
		request.setAttribute("productCode", product);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/product/updateForm.jsp");
		rd.forward(request, response);
	}

	
	
}
