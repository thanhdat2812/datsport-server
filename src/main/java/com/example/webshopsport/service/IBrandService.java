package com.example.webshopsport.service;

import java.util.ArrayList;

import com.example.webshopsport.model.BrandModel;

public interface IBrandService {
	
	public ArrayList<BrandModel> getAllBrandByStatus(int status);
	public BrandModel findBrandById(int id);
	public BrandModel createBrand(BrandModel brandModel);
	public int updateBrand(BrandModel brandModel);

}
