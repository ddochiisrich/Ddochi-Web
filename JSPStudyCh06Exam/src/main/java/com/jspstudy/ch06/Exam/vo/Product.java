package com.jspstudy.ch06.Exam.vo;

public class Product {
	private String productName;
	private String productImg;
	private String manufacturer;
	private String price;
	private String productCode;
	private String productcomment;

	public Product() {}

	
	
	public Product(String productName, String productImg, String manufacturer, String price, String productCode,
			String productcomment) {
		this.productName = productName;
		this.productImg = productImg;
		this.manufacturer = manufacturer;
		this.price = price;
		this.productCode = productCode;
		this.productcomment = productcomment;
	}



	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductcomment() {
		return productcomment;
	}

	public void setProductcomment(String productcomment) {
		this.productcomment = productcomment;
	}
	
	
	
}

         