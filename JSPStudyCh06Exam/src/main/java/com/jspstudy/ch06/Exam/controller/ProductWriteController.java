package com.jspstudy.ch06.Exam.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.ch06.Exam.dao.ProductDao;
import com.jspstudy.ch06.Exam.vo.Product;

@WebServlet("/addProcess")
public class ProductWriteController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String productName = request.getParameter("productName");
		String productPrice = request.getParameter("productPrice");
		String productCode = request.getParameter("productCode");
		String productManufacturer = request.getParameter("productManufacturer");
		String productComment = request.getParameter("productComment");
		String productImg = request.getParameter("productImg");
		
		Product product = new Product();
		
		product.setProductName(productName);
		product.setPrice(productPrice);
		product.setProductCode(productCode);
		product.setManufacturer(productManufacturer);
		product.setProductcomment(productComment);
		product.setProductImg(productImg);
		
		ProductDao dao = new ProductDao();
		dao.addProduct(product);
		
		response.sendRedirect("productList");
		
	}
	
	
	
}
