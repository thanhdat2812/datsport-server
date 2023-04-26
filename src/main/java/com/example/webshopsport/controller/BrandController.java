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

import com.example.webshopsport.model.BrandModel;
import com.example.webshopsport.model.ResponseObject;
import com.example.webshopsport.service.IBrandService;

@RestController
@CrossOrigin
public class BrandController {
	
	@Autowired
	IBrandService brandService;
	
	@GetMapping("/api/getallbrandbystatus/{status}")
	public ResponseEntity<ResponseObject> getAllBrand(@PathVariable("status") int status) {
		ArrayList<BrandModel> listAllBrand = brandService.getAllBrandByStatus(status);
		if(listAllBrand != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Get Brand Success", listAllBrand));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Don't Brand To Get", null));
	}
	
	@GetMapping("/api/getbrandbyid/{id}")
	public ResponseEntity<ResponseObject> getBrandById(@PathVariable("id") int id) {
		BrandModel brandModel = brandService.findBrandById(id);
		if(brandModel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Get Brand Success", brandModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Don't Brand To Get", null));
	}
	
	@PostMapping("/admin/createbrand")
	public ResponseEntity<ResponseObject> createBrand(@RequestBody BrandModel brandModel) {
		BrandModel brand = brandService.createBrand(brandModel);
		if(brand != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Create Brand Success", brand));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Brand Exists", null));
	}
	
	@PostMapping("/admin/updatebrand")
	public ResponseEntity<ResponseObject> updateBrand(@RequestBody BrandModel brandModel) {
		int result = brandService.updateBrand(brandModel);
		if(result == 1) {
			BrandModel checkbrandModel = brandService.findBrandById(brandModel.getBrand_id());
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Update Brand Success", checkbrandModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Update Failed", null));
	}

}
