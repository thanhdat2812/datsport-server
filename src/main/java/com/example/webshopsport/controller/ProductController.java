package com.example.webshopsport.controller;

import java.text.ParseException;
import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webshopsport.entities.Product;
import com.example.webshopsport.model.ProductModel;
import com.example.webshopsport.model.ResponseObject;
import com.example.webshopsport.service.IProductService;

@RestController
@CrossOrigin
public class ProductController {
	
	@Autowired
	IProductService productService;
	
	@GetMapping("/api/productpaging")
	public ResponseEntity<ResponseObject> getPaginated(@PathParam("pageNumber") int pageNumber, @PathParam("pageSize") int pageSize, @PathParam("keyword") String keyword) {
		Page<Product> listProduct = productService.findPaginated(pageNumber, pageSize, keyword);
		if(listProduct != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Get List Product Success", listProduct));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Don't Product To Get", null));
	}
	
	@GetMapping("/api/getproducthot")
	public ResponseEntity<ResponseObject> getProductHot() {
		ArrayList<ProductModel> listProductModel = productService.getProductHot();
		if(listProductModel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Get List Product Hot Success", listProductModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Don't Product Hot To Get", null));
	}
	
	@GetMapping("/api/getallproduct/{status}")
	public ResponseEntity<ResponseObject> getAllProduct(@PathVariable("status") int status) {
		ArrayList<ProductModel> listProduct = productService.getAllProduct(status);
		if(listProduct != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Get List Product Success", listProduct));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Don't Product To Get", null));
	}
	
	@PostMapping("/admin/createproduct")
	public ResponseEntity<ResponseObject> craeteProduct(@RequestBody ProductModel productModel) throws ParseException {
		Product product = productService.createProduct(productModel);
		if(product != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Craete Product Success", product));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Create Product Failed, Product Exists", null));
		
	}
	
	@PostMapping("/admin/updateproduct")
	public ResponseEntity<ResponseObject> updateProduct(@RequestBody ProductModel productModel) throws ParseException {
		ProductModel product = productService.updateProduct(productModel);
		if(product != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Update Product Success", product));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Update Product Failed", null));
		
	}
	
	@GetMapping("/api/searchproductbyname/{name}")
	public ResponseEntity<ResponseObject> searchProductByName(@PathVariable("name") String name) {
		ArrayList<ProductModel> listProductModel = productService.searchProductByName(name);
		if(listProductModel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Find Product Success", listProductModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Product Not Found", null));
	}
	
	@GetMapping("/api/searchproductbybrandid/{brandid}")
	public ResponseEntity<ResponseObject> searchProductByBrandId(@PathVariable("brandid") int brandid) {
		ArrayList<ProductModel> listProductModel = productService.searchProductByBrandId(brandid);
		if(listProductModel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Find Product Success", listProductModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Product Not Found", null));
	}
	
	@GetMapping("/api/searchproductbycategoryid/{categoryid}")
	public ResponseEntity<ResponseObject> searchProductByCategoryId(@PathVariable("categoryid") int categoryid) {
		ArrayList<ProductModel> listProductModel = productService.searchProductByCategoryId(categoryid);
		if(listProductModel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Find Product Success", listProductModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Product Not Found", null));
	}
	
	@GetMapping("/api/getproductbyid/{idproduct}")
	public ResponseEntity<ResponseObject> getProductById(@PathVariable("idproduct") int idproduct) {
		Product product = productService.getProductByIdProduct(idproduct);
		if(product != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Find Product Success", product));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Product Not Found", null));
	}
	
}
