package com.example.webshopsport.model;

public class BrandModel {
	
	private int brand_id;
	private String brand_name;
	private int brand_status;
	
	public BrandModel() {
		
	}

	public BrandModel(int brand_id, String brand_name, int brand_status) {
		this.brand_id = brand_id;
		this.brand_name = brand_name;
		this.brand_status = brand_status;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public int getBrand_status() {
		return brand_status;
	}

	public void setBrand_status(int brand_status) {
		this.brand_status = brand_status;
	}

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
}
