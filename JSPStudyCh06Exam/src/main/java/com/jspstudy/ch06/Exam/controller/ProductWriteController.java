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

@WebServlet("/addProcess")
public class ProductWriteController extends HttpServlet{

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
        
        // 파일 업로드 처리
        String realPath = request.getServletContext().getRealPath(uploadDir);
        int maxFileSize = 10 * 1024 * 1024; // 최대 파일 크기 설정 (10MB)
        String encoding = "UTF-8";
        
        MultipartRequest multi = new MultipartRequest(request, realPath, maxFileSize, encoding, new DefaultFileRenamePolicy());
        
        // 폼 데이터 가져오기
        String productName = multi.getParameter("productName");
        String productPrice = multi.getParameter("productPrice");
        String productCode = multi.getParameter("productCode");
        String productManufacturer = multi.getParameter("productManufacturer");
        String productComment = multi.getParameter("productComment");
        String productImg = multi.getParameter("productImg");
        
        // Product 객체 생성 및 데이터 설정
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(productPrice);
        product.setProductCode(productCode);
        product.setManufacturer(productManufacturer);
        product.setProductcomment(productComment);
        product.setProductImg(productImg);
        
        // 업로드된 파일명 설정
        String fileName = multi.getFilesystemName("productImg");
        System.out.println("업로드 된 파일명 : " + fileName);
        System.out.println("원본 파일명 : " + multi.getOriginalFileName("productImg"));
        
        // Product 객체에 업로드된 파일명 설정
        product.setProductImg(fileName);
        
        // 파일이 업로드되지 않은 경우 처리
        if(product.getProductImg() == null){
            System.out.println("파일이 업로드 되지 않았음");
        }
        
        // ProductDao를 사용하여 상품 추가
        ProductDao dao = new ProductDao();
        dao.addProduct(product);
        
        // 상품 목록 페이지로 리다이렉트
        response.sendRedirect("productList");
        
    }
}
