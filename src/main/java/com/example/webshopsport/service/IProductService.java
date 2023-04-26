package com.example.webshopsport.service;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.data.domain.Page;

import com.example.webshopsport.entities.Product;
import com.example.webshopsport.model.ProductModel;

public interface IProductService {
	
	public ArrayList<ProductModel> getAllProduct(int status);
	public Product createProduct(ProductModel productModel) throws ParseException;
	public Product getProductByIdProduct(int idProduct);
	public ArrayList<ProductModel> searchProductByName(String name);
	public ArrayList<ProductModel> searchProductByBrandId(int brandId);
	public ArrayList<ProductModel> searchProductByCategoryId(int categoryId);
	public ProductModel updateProduct(ProductModel productModel) throws ParseException;
	public int updateQuantity(int quantity, int productId);
	Page<Product> findPaginated(int pageNo, int pageSize, String keyword);
	ArrayList<ProductModel> getProductHot();

}
