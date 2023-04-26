package com.example.webshopsport.model;

public class CategoryModel {
	
	private int categoryId;
	private String categoryName;
	private int categoryStatus;
	
	public CategoryModel() {
		
	}

	public CategoryModel(int categoryId, String categoryName, int categoryStatus) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryStatus = categoryStatus;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(int categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	
}
