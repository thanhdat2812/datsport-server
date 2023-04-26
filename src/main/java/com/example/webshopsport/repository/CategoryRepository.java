package com.example.webshopsport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.webshopsport.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query("SELECT c FROM Category c WHERE c.categoryStatus = ?1")
	public List<Category> getAllCategoryByStatus(int status);
	
	@Query("SELECT c FROM Category c WHERE c.categoryId = ?1")
	public Category findCategoryByCategoryId(int categoryId);
	
	@Query("SELECT c FROM Category c WHERE c.categoryName = ?1")
	public Category findCategoryByNameAndStatus(String categoryName);
	
	@Transactional
	@Modifying
	@Query("UPDATE Category c SET c.categoryName = ?1, c.categoryStatus = ?2 WHERE c.categoryId = ?3")
	public int updateCategoryById(String categoryName, int categoryStatus, int categoryId);

}
