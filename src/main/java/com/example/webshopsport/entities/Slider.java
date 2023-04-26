package com.example.webshopsport.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the slider database table.
 * 
 */
@Entity
@Cacheable(false)
@NamedQuery(name="Slider.findAll", query="SELECT s FROM Slider s")
public class Slider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="slider_id")
	private int sliderId;

	@Column(name="slider_image")
	private String sliderImage;

	@Column(name="slider_name")
	private String sliderName;

	@Column(name="slider_status")
	private int sliderStatus;

	public Slider() {
	}

	public int getSliderId() {
		return this.sliderId;
	}

	public void setSliderId(int sliderId) {
		this.sliderId = sliderId;
	}

	public String getSliderImage() {
		return this.sliderImage;
	}

	public void setSliderImage(String sliderImage) {
		this.sliderImage = sliderImage;
	}

	public String getSliderName() {
		return this.sliderName;
	}

	public void setSliderName(String sliderName) {
		this.sliderName = sliderName;
	}

	public int getSliderStatus() {
		return this.sliderStatus;
	}

	public void setSliderStatus(int sliderStatus) {
		this.sliderStatus = sliderStatus;
	}

}