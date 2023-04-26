package com.example.webshopsport.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;


/**
 * The persistent class for the brands database table.
 * 
 */
@Entity
@Table(name="brands")
@Cacheable(false)
@NamedQuery(name="Brand.findAll", query="SELECT b FROM Brand b")
public class Brand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="brand_id")
	private int brandId;

	@Column(name="brand_name")
	private String brandName;

	@Column(name="brand_status")
	private int brandStatus;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="brand")
	@JsonBackReference
	private List<Product> products;

	public Brand() {
	}

	public int getBrandId() {
		return this.brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getBrandStatus() {
		return this.brandStatus;
	}

	public void setBrandStatus(int brandStatus) {
		this.brandStatus = brandStatus;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setBrand(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setBrand(null);

		return product;
	}

}