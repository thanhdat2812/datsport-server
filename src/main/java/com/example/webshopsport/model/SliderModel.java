package com.example.webshopsport.model;

public class SliderModel {
	
	private int slider_id;
	private String slider_name;
	private String slider_image;
	private int slider_status;
	
	public SliderModel() {

	}

	public SliderModel(int slider_id, String slider_name, String slider_image, int slider_status) {
		this.slider_id = slider_id;
		this.slider_name = slider_name;
		this.slider_image = slider_image;
		this.slider_status = slider_status;
	}

	public String getSlider_name() {
		return slider_name;
	}

	public void setSlider_name(String slider_name) {
		this.slider_name = slider_name;
	}

	public String getSlider_image() {
		return slider_image;
	}

	public void setSlider_image(String slider_image) {
		this.slider_image = slider_image;
	}

	public int getSlider_status() {
		return slider_status;
	}

	public void setSlider_status(int slider_status) {
		this.slider_status = slider_status;
	}

	public int getSlider_id() {
		return slider_id;
	}

	public void setSlider_id(int slider_id) {
		this.slider_id = slider_id;
	}
}
