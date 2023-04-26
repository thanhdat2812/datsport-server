package com.example.webshopsport.serviceImp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.webshopsport.entities.Category;
import com.example.webshopsport.model.CategoryModel;
import com.example.webshopsport.repository.CategoryRepository;
import com.example.webshopsport.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public ArrayList<CategoryModel> getAllCategoryByStatus(int status) {
		
		ArrayList<Category> listCategory = new ArrayList<Category>();
		if(status == -1) {
			listCategory = (ArrayList<Category>) categoryRepository.findAll();
		}
		else {
			listCategory = (ArrayList<Category>) categoryRepository.getAllCategoryByStatus(status);
		}
		
		ArrayList<CategoryModel> listCategoryModel = new ArrayList<CategoryModel>();
		for (Category Category2 : listCategory) {
			listCategoryModel.add(convertToDTO(Category2));
		}
		return listCategoryModel;
	}

	@Override
	public CategoryModel create(CategoryModel categoryModel) {
		Category category = convertToEntity(categoryModel);
		Category category2 = categoryRepository.findCategoryByNameAndStatus(category.getCategoryName());
		if(category2 != null) {
			return null;
		}
		categoryRepository.save(category);
		Category result = categoryRepository.findCategoryByNameAndStatus(category.getCategoryName());
		return convertToDTO(result);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateCategory(CategoryModel categoryModel) {
		Optional<Category> categoryCheck = categoryRepository.findById(categoryModel.getCategoryId());
		if(categoryCheck == null) {
			return 0;
		}
		Category category = convertToEntity(categoryModel);
		categoryRepository.updateCategoryById(category.getCategoryName(), category.getCategoryStatus(), category.getCategoryId());
		return 1;
	}
	
	@Override
	public CategoryModel findCategoryByCategoryId(int categoryId) {
		Category category = categoryRepository.findCategoryByCategoryId(categoryId);
		if(category != null) {
			return convertToDTO(category);
		}
		return null;
	}
	
	public Category convertToEntity(CategoryModel categoryModel) {
		Category category = new Category();
		category.setCategoryId(categoryModel.getCategoryId());
		category.setCategoryName(categoryModel.getCategoryName());
		category.setCategoryStatus(categoryModel.getCategoryStatus());
		return category;
	}
	
	public CategoryModel convertToDTO(Category category) {
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setCategoryId(category.getCategoryId());
		categoryModel.setCategoryName(category.getCategoryName());
		categoryModel.setCategoryStatus(category.getCategoryStatus());
		return categoryModel;
	}

}
