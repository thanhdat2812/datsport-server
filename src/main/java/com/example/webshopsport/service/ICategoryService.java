package com.example.webshopsport.service;

import java.util.ArrayList;

import com.example.webshopsport.model.CategoryModel;

public interface ICategoryService {
	
	public ArrayList<CategoryModel> getAllCategoryByStatus(int status);
	public CategoryModel create(CategoryModel categoryModel);
	public int updateCategory(CategoryModel categoryModel);
	public CategoryModel findCategoryByCategoryId(int categoryId);
}
