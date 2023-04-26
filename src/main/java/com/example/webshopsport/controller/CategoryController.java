package com.example.webshopsport.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webshopsport.model.CategoryModel;
import com.example.webshopsport.model.ResponseObject;
import com.example.webshopsport.service.ICategoryService;

@RestController
@CrossOrigin
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping("/api/findallcategorybystatus/{status}")
	public ResponseEntity<ResponseObject> getAllCategoryByStatus(@PathVariable("status") int status) {
		ArrayList<CategoryModel> listCategoryModel = categoryService.getAllCategoryByStatus(status);
		if(listCategoryModel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Get Category Success", listCategoryModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Don't Category To Get", null));
	}
	
	@GetMapping("/api/getcategorybyid/{id}")
	public ResponseEntity<ResponseObject> getCategoryById(@PathVariable("id") int id) {
		CategoryModel categoryModel = categoryService.findCategoryByCategoryId(id);
		if(categoryModel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Get Category Success", categoryModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Don't Category To Get", null));
	}
	
	@PostMapping("/admin/createcategory")
	public ResponseEntity<ResponseObject> createCategory(@RequestBody CategoryModel categoryModel) {
		CategoryModel newCategory = categoryService.create(categoryModel);
		if(newCategory != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Craete Category Success", newCategory));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Category Exists", null));
	}
	
	@PostMapping("/admin/updatecategory")
	public ResponseEntity<ResponseObject> updateCategory(@RequestBody CategoryModel categoryModel) {
		int result = categoryService.updateCategory(categoryModel);
		if(result == 1) {
			CategoryModel cateModel = categoryService.findCategoryByCategoryId(categoryModel.getCategoryId());
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Update Category Success", cateModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Update Failed", null));
	}
	
}
