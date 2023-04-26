package com.example.webshopsport.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.webshopsport.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
	
	
	@Query("SELECT b FROM Brand b WHERE b.brandStatus = ?1")
	public ArrayList<Brand> getAllBrandByStatus(int status);
	
	@Query("SELECT b FROM Brand b WHERE b.brandId = ?1")
	public Brand findBrandByBrandId(int brandId);
	
	@Query("SELECT b FROM Brand b WHERE b.brandName = ?1")
	public Brand findBrandByNameAndStatus(String  brandName);
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Modifying
	@Query("UPDATE Brand b SET b.brandName = ?1, b.brandStatus = ?2 WHERE b.brandId = ?3")
	public int updateBrandById(String brandName, int brandStatus, int brandId);
	 
	 
}
