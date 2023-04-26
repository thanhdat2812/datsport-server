package com.example.webshopsport.model;

public class ProductModel {
	
	private int productId;
	private int categoryId;
	private int brandId;
	private String productCreateDate;
	private String productCreateUser;
	private String productUpdateDate;
	private String productUpdateUser;
	private String productDescription;
	private String productImage1;
	private String productImage2;
	private String productImage3;
	private String productImage4;
	private String productImage5;
	private String productName;
	private int productPrice;
	private int productQuantity;
	private boolean productHot;
	private int productStatus;
	
	public ProductModel() {
		
	}

	public ProductModel(int productId, int categoryId, int brandId, String productCreateDate, String productCreateUser,
			String productUpdateDate, String productUpdateUser, String productDescription, String productImage1,
			String productImage2, String productImage3, String productImage4, String productImage5, String productName,
			int productPrice, int productQuantity, boolean productHot, int productStatus) {
		this.productId = productId;
		this.categoryId = categoryId;
		this.brandId = brandId;
		this.productCreateDate = productCreateDate;
		this.productCreateUser = productCreateUser;
		this.productUpdateDate = productUpdateDate;
		this.productUpdateUser = productUpdateUser;
		this.productDescription = productDescription;
		this.productImage1 = productImage1;
		this.productImage2 = productImage2;
		this.productImage3 = productImage3;
		this.productImage4 = productImage4;
		this.productImage5 = productImage5;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.productHot = productHot;
		this.productStatus = productStatus;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getProductCreateDate() {
		return productCreateDate;
	}

	public void setProductCreateDate(String productCreateDate) {
		this.productCreateDate = productCreateDate;
	}

	public String getProductCreateUser() {
		return productCreateUser;
	}

	public void setProductCreateUser(String productCreateUser) {
		this.productCreateUser = productCreateUser;
	}

	public String getProductUpdateDate() {
		return productUpdateDate;
	}

	public void setProductUpdateDate(String productUpdateDate) {
		this.productUpdateDate = productUpdateDate;
	}

	public String getProductUpdateUser() {
		return productUpdateUser;
	}

	public void setProductUpdateUser(String productUpdateUser) {
		this.productUpdateUser = productUpdateUser;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductImage1() {
		return productImage1;
	}

	public void setProductImage1(String productImage1) {
		this.productImage1 = productImage1;
	}

	public String getProductImage2() {
		return productImage2;
	}

	public void setProductImage2(String productImage2) {
		this.productImage2 = productImage2;
	}

	public String getProductImage3() {
		return productImage3;
	}

	public void setProductImage3(String productImage3) {
		this.productImage3 = productImage3;
	}

	public String getProductImage4() {
		return productImage4;
	}

	public void setProductImage4(String productImage4) {
		this.productImage4 = productImage4;
	}

	public String getProductImage5() {
		return productImage5;
	}

	public void setProductImage5(String productImage5) {
		this.productImage5 = productImage5;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public boolean isProductHot() {
		return productHot;
	}

	public void setProductHot(boolean productHot) {
		this.productHot = productHot;
	}

	public int getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}

}
