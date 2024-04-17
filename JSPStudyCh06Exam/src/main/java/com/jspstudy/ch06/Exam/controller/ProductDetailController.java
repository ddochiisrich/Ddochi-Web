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

@WebServlet("/detailProduct")
public class ProductDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String productCode = request.getParameter("productCode");
		
		ProductDao dao = new ProductDao();
		Product product = dao.getProduct(productCode);
		
		request.setAttribute("product", product);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/product/productDetail.jsp");
		rd.forward(request, response);
		
	}

	
	
}