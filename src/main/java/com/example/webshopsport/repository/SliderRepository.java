package com.example.webshopsport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.webshopsport.entities.Slider;

@Repository
public interface SliderRepository extends JpaRepository<Slider, Integer> {
	
	@Query("SELECT s FROM Slider s WHERE s.sliderStatus = ?1")
	public List<Slider> getAllSliderByStatus(int status);
	
	@Query("SELECT s FROM Slider s WHERE s.sliderId = ?1")
	public Slider findSliderBySliderId(int sliderId);
	
	@Query("SELECT s FROM Slider s WHERE s.sliderName = ?1")
	public Slider findSliderByName(String  sliderName);

}
