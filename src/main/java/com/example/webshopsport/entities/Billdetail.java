package com.example.webshopsport.entities;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the billdetail database table.
 * 
 */
@Entity
@Cacheable(false)
@NamedQuery(name="Billdetail.findAll", query="SELECT b FROM Billdetail b")
public class Billdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="billdetail_id")
	private int billdetailId;

	@Column(name="billdetail_price")
	private double billdetailPrice;

	@Column(name="billdetail_quantity")
	private int billdetailQuantity;
	
	@Column(name="billdetail_size")
	private String billdetailSize;

	//bi-directional many-to-one association to Bill
	@ManyToOne
	@JoinColumn(name="bill_id")
	private Bill bill;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public Billdetail() {
	}

	public int getBilldetailId() {
		return this.billdetailId;
	}

	public void setBilldetailId(int billdetailId) {
		this.billdetailId = billdetailId;
	}

	public double getBilldetailPrice() {
		return this.billdetailPrice;
	}

	public void setBilldetailPrice(double billdetailPrice) {
		this.billdetailPrice = billdetailPrice;
	}

	public int getBilldetailQuantity() {
		return this.billdetailQuantity;
	}

	public void setBilldetailQuantity(int billdetailQuantity) {
		this.billdetailQuantity = billdetailQuantity;
	}

	public String getBilldetailSize() {
		return billdetailSize;
	}

	public void setBilldetailSize(String billdetailSize) {
		this.billdetailSize = billdetailSize;
	}

	public Bill getBill() {
		return this.bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}