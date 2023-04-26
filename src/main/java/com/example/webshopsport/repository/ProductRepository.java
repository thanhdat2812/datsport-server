package com.example.webshopsport.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.webshopsport.entities.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
	
	@Query("SELECT p FROM Product p WHERE p.productName LIKE %?1% OR p.brand.brandId LIKE %?1% OR p.category.categoryId LIKE %?1%")
	public Page<Product> fillAll(String keyword, Pageable pageable );
	
	@Query("SELECT p FROM Product p WHERE p.productHot = 1 ORDER BY p.productId DESC")
	public List<Product> getProductHot();
	
	@Query("SELECT p FROM Product p ORDER BY p.productId DESC")
	public List<Product> getAllProduct();
	
	@Query("SELECT p FROM Product p WHERE p.productStatus = ?1 ORDER BY p.productId DESC")
	public List<Product> getAllProductByStatus(int status);
	
	@Query("SELECT p FROM Product p JOIN Category c ON p.category = c.id WHERE p.productId = ?1")
	public Product findProductById(int id);

	@Query("SELECT p FROM Product p WHERE p.productName = ?1")
	public Product findProductByName(String name);
	
	@Query("SELECT p FROM Product p WHERE p.productName LIKE %?1% AND p.productStatus = 1")
	public List<Product> searchProductByName(String name);
	
	@Query("SELECT p FROM Product p WHERE p.brand.brandId = ?1 AND p.productStatus = 1")
	public List<Product> searchProductByBrandId(int brandId);
	
	@Query("SELECT p FROM Product p WHERE p.category.categoryId = ?1 AND p.productStatus = 1")
	public List<Product> searchProductByCategoryId(int categoryId);
	
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.productQuantity = ?1 WHERE p.productId = ?2")
	public int updateProductQuantity(int quantity, int productId);
	
	
	
}
