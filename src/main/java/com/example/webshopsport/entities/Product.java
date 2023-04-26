package com.example.webshopsport.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Cacheable(false)
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int productId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="product_create_date")
	private Date productCreateDate;

	@Column(name="product_create_user")
	private String productCreateUser;

	@Column(name="product_description")
	private String productDescription;

	@Column(name="product_image_1")
	private String productImage1;
	
	@Column(name="product_image_2")
	private String productImage2;
	
	@Column(name="product_image_3")
	private String productImage3;
	
	@Column(name="product_image_4")
	private String productImage4;
	
	@Column(name="product_image_5")
	private String productImage5;

	@Column(name="product_name")
	private String productName;

	@Column(name="product_price")
	private int productPrice;

	@Column(name="product_quantity")
	private int productQuantity;

	@Column(name="product_hot")
	private boolean productHot;

	@Column(name="product_status")
	private int productStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="product_update_date")
	private Date productUpdateDate;

	@Column(name="product_update_user")
	private String productUpdateUser;

	//bi-directional many-to-one association to Billdetail
	@OneToMany(mappedBy="product")
//	@JsonBackReference
	@JsonIgnore
	private List<Billdetail> billdetails;

	//bi-directional many-to-one association to Brand
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Date getProductCreateDate() {
		return this.productCreateDate;
	}

	public void setProductCreateDate(Date productCreateDate) {
		this.productCreateDate = productCreateDate;
	}

	public String getProductCreateUser() {
		return this.productCreateUser;
	}

	public void setProductCreateUser(String productCreateUser) {
		this.productCreateUser = productCreateUser;
	}

	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductImage1() {
		return productImage1;
	}

	public void setProductImage1(String productImage1) {
		this.productImage1 = productImage1;
	}

	public String getProductImage2() {
		return productImage2;
	}

	public void setProductImage2(String productImage2) {
		this.productImage2 = productImage2;
	}

	public String getProductImage3() {
		return productImage3;
	}

	public void setProductImage3(String productImage3) {
		this.productImage3 = productImage3;
	}

	public String getProductImage4() {
		return productImage4;
	}

	public void setProductImage4(String productImage4) {
		this.productImage4 = productImage4;
	}

	public String getProductImage5() {
		return productImage5;
	}

	public void setProductImage5(String productImage5) {
		this.productImage5 = productImage5;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return this.productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public boolean getProductHot() {
		return productHot;
	}

	public void setProductHot(boolean productHot) {
		this.productHot = productHot;
	}

	public int getProductStatus() {
		return this.productStatus;
	}

	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}

	public Date getProductUpdateDate() {
		return this.productUpdateDate;
	}

	public void setProductUpdateDate(Date productUpdateDate) {
		this.productUpdateDate = productUpdateDate;
	}

	public String getProductUpdateUser() {
		return this.productUpdateUser;
	}

	public void setProductUpdateUser(String productUpdateUser) {
		this.productUpdateUser = productUpdateUser;
	}

	public List<Billdetail> getBilldetails() {
		return this.billdetails;
	}

	public void setBilldetails(List<Billdetail> billdetails) {
		this.billdetails = billdetails;
	}

	public Billdetail addBilldetail(Billdetail billdetail) {
		getBilldetails().add(billdetail);
		billdetail.setProduct(this);

		return billdetail;
	}

	public Billdetail removeBilldetail(Billdetail billdetail) {
		getBilldetails().remove(billdetail);
		billdetail.setProduct(null);

		return billdetail;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}