package com.example.webshopsport.serviceImp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.webshopsport.entities.Brand;
import com.example.webshopsport.entities.Category;
import com.example.webshopsport.entities.Product;
import com.example.webshopsport.model.ProductModel;
import com.example.webshopsport.repository.BrandRepository;
import com.example.webshopsport.repository.CategoryRepository;
import com.example.webshopsport.repository.ProductRepository;
import com.example.webshopsport.service.IProductService;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Page<Product> findPaginated(int pageNo, int pageSize, String keyword) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		String key = keyword.toString();
		if(keyword != "") {
			return productRepository.fillAll(key, paging);
		}
		return productRepository.findAll(paging);
	}
	
	@Override
	public ArrayList<ProductModel> getProductHot() {
		ArrayList<Product> listProductHot = (ArrayList<Product>) productRepository.getProductHot();
		ArrayList<ProductModel> listProductModels = new ArrayList<>();
		if(listProductHot == null) {
			return null;
		}
		for (Product product : listProductHot) {
			listProductModels.add(convertToDTO(product));
		}
		return listProductModels;
	}

	@Override
	public ArrayList<ProductModel> getAllProduct(int status) {
		ArrayList<Product> listProduct = new ArrayList<Product>();
		if(status == -1) {
			listProduct = (ArrayList<Product>) productRepository.getAllProduct(); 
		}
		else {
			listProduct = (ArrayList<Product>) productRepository.getAllProductByStatus(status);
		}
		
		ArrayList<ProductModel> listProductModel = new ArrayList<>();
		for (Product product : listProduct) {
			listProductModel.add(convertToDTO(product));
		}
		return listProductModel;
	}
	
	@Override
	public Product getProductByIdProduct(int idProduct) {
		Product product = productRepository.findProductById(idProduct);
		return product;
	}

	@Override
	public Product createProduct(ProductModel productModel) throws ParseException {
		Product checkProduct = productRepository.findProductByName(productModel.getProductName());
		if(checkProduct == null) {
			Product convertProductToEntity = convertToEntity(productModel);
			Product product  = productRepository.save(convertProductToEntity);
			
			return product;
		}
		return null;
	}

	@Override
	public ArrayList<ProductModel> searchProductByName(String name) {
		ArrayList<Product> product = (ArrayList<Product>) productRepository.searchProductByName(name);
		ArrayList<ProductModel> listProductModels = new ArrayList<>();
		if(product == null) {
			return null;
		}
		for (Product product2 : product) {
			listProductModels.add(convertToDTO(product2));
		}
		return listProductModels;
	}
	
	@Override
	public ArrayList<ProductModel> searchProductByBrandId(int brandId) {
		ArrayList<Product> product = (ArrayList<Product>) productRepository.searchProductByBrandId(brandId);
		ArrayList<ProductModel> listProductModels = new ArrayList<>();
		if(product == null) {
			return null;
		}
		for (Product product2 : product) {
			listProductModels.add(convertToDTO(product2));
		}
		return listProductModels;
	}

	@Override
	public ArrayList<ProductModel> searchProductByCategoryId(int categoryId) {
		ArrayList<Product> product = (ArrayList<Product>) productRepository.searchProductByCategoryId(categoryId);
		ArrayList<ProductModel> listProductModels = new ArrayList<>();
		if(product == null) {
			return null;
		}
		for (Product product2 : product) {
			listProductModels.add(convertToDTO(product2));
		}
		return listProductModels;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ProductModel updateProduct(ProductModel productModel) throws ParseException {
		// TODO Auto-generated method stub
		Optional<Product> product = productRepository.findById(productModel.getProductId());
		if(product.isPresent()) {
			 
			Product product2 = convertToEntity(productModel);
			product2.setProductId(product.get().getProductId());
			productRepository.save(product2);
			return convertToDTO(product2);
		}
		return null;
	}

	@Override
	public int updateQuantity(int quantity, int productId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public Product convertToEntity(ProductModel productModel) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		Product product = new Product();
		Category category = categoryRepository.findCategoryByCategoryId(productModel.getCategoryId());
		Brand brand = brandRepository.findBrandByBrandId(productModel.getBrandId());
		product.setProductId(productModel.getProductId());
		product.setCategory(category);
		product.setBrand(brand);
		product.setProductName(productModel.getProductName());
		product.setProductImage1(productModel.getProductImage1());
		product.setProductImage2(productModel.getProductImage2());
		product.setProductImage3(productModel.getProductImage3());
		product.setProductImage4(productModel.getProductImage4());
		product.setProductImage5(productModel.getProductImage5());
		product.setProductPrice(productModel.getProductPrice());
		product.setProductQuantity(productModel.getProductQuantity());
		product.setProductHot(productModel.isProductHot());
		product.setProductDescription(productModel.getProductDescription());
		product.setProductCreateDate(df.parse(productModel.getProductCreateDate().toString()));
		product.setProductCreateUser(productModel.getProductCreateDate());
		product.setProductUpdateDate(df.parse(productModel.getProductUpdateDate().toString()));
		product.setProductUpdateUser(productModel.getProductUpdateUser());
		product.setProductStatus(productModel.getProductStatus());
		return product;
	}
	
	public ProductModel convertToDTO(Product product) {
		ProductModel productModel = new ProductModel();
		productModel.setProductId(product.getProductId());
		productModel.setCategoryId(product.getCategory().getCategoryId());
		productModel.setBrandId(product.getBrand().getBrandId());
		productModel.setProductName(product.getProductName());
		productModel.setProductImage1(product.getProductImage1());
		productModel.setProductImage2(product.getProductImage2());
		productModel.setProductImage3(product.getProductImage3());
		productModel.setProductImage4(product.getProductImage4());
		productModel.setProductImage5(product.getProductImage5());
		productModel.setProductPrice(product.getProductPrice());
		productModel.setProductQuantity(product.getProductQuantity());
		productModel.setProductHot(product.getProductHot());
		productModel.setProductDescription(product.getProductDescription());
		productModel.setProductCreateDate(product.getProductCreateDate().toString());
		productModel.setProductCreateUser(product.getProductCreateUser());
		productModel.setProductUpdateDate(product.getProductUpdateDate().toString());
		productModel.setProductUpdateUser(product.getProductUpdateUser());
		productModel.setProductStatus(product.getProductStatus());
		return productModel;
	}

}
