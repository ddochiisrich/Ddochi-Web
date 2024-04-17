package com.jspstudy.ch06.Exam.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.ch06.Exam.dao.ProductDao;
import com.jspstudy.ch06.Exam.vo.Product;

@WebServlet("/productList")
public class ProductListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProductDao dao = new ProductDao();
		ArrayList<Product> productList = dao.productList();
		
		request.setAttribute("productList", productList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/product/productList.jsp");
		
		rd.forward(request, response);



	}

}