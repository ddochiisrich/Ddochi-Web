package com.jspstudy.ch06.Exam.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.ch06.Exam.dao.ProductDao;
import com.jspstudy.ch06.Exam.vo.Product;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/updateProcess")
public class ProductUpdateController extends HttpServlet{

    private static String uploadDir;
    private static File parentFile;
    
    public void init() {
        // 파일 업로드 디렉토리 설정
        uploadDir = getServletContext().getInitParameter("uploadDir");
        String realPath = getServletContext().getRealPath(uploadDir);
        parentFile = new File(realPath);
        
        // 파일 업로드 디렉토리가 없으면 생성
        if(!(parentFile.exists() && parentFile.isDirectory())) {
            parentFile.mkdir();
        }
        System.out.println("init - " + parentFile);
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		request.setCharacterEncoding("UTF-8");
		
		String realPath = request.getServletContext().getRealPath(uploadDir);
        int maxFileSize = 10 * 1024 * 1024; // 최대 파일 크기 설정 (10MB)
        String encoding = "UTF-8";
        
        MultipartRequest multi = new MultipartRequest(request, realPath, maxFileSize, encoding, new DefaultFileRenamePolicy());
		
		String name = multi.getParameter("updateName");
		System.out.println(name);
		String img = multi.getParameter("updateImg");
		System.out.println(img);
		String manu = multi.getParameter("updateManufacturer");
		System.out.println(manu);
		String price = multi.getParameter("updatePrice");
		System.out.println(price);
		String code = multi.getParameter("updateCode");
		System.out.println(code);
		String comm = multi.getParameter("updateComment");
		System.out.println(comm);
		
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
