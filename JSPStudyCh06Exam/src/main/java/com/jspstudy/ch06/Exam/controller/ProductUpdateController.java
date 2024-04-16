package com.jspstudy.ch06.Exam.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.ch06.Exam.dao.ProductDao;
import com.jspstudy.ch06.Exam.vo.Product;

@WebServlet("/updateProcess")
public class ProductUpdateController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String name = null;
		String img = null;
		String manu = null;
		String price = null;
		String code = null;
		String comm = null;
		
		name = request.getParameter("productName");
		img = request.getParameter("img");
		manu = request.getParameter("manu");
		price = request.getParameter("price");
		code = request.getParameter("code");
		comm = request.getParameter("comm");
		
		ProductDao dao = new ProductDao();
		
		Product product = new Product();
		product.setProductName(name);
		product.setProductImg(img);
		product.setManufacturer(manu);
		product.setPrice(price);
		product.setProductCode(code);
		product.setProductcomment(comm);
		
		dao.updateProduct(product);
		
		response.sendRedirect("productList");
		
		
		
		
	}

	
	
}
