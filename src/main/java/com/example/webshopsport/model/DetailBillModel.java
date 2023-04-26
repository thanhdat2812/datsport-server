package com.example.webshopsport.model;

public class DetailBillModel {
	
	private int billdetail_id;
	private int bill_id;
	private int product_id;
	private int billdetail_price;
	private int billdetail_quantity;
	
	public DetailBillModel() {
		
	}

	public DetailBillModel(int billdetail_id, int bill_id, int product_id, int billdetail_price,
			int billdetail_quantity) {
		this.billdetail_id = billdetail_id;
		this.bill_id = bill_id;
		this.product_id = product_id;
		this.billdetail_price = billdetail_price;
		this.billdetail_quantity = billdetail_quantity;
	}

	public int getBill_id() {
		return bill_id;
	}

	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getBilldetail_price() {
		return billdetail_price;
	}

	public void setBilldetail_price(int billdetail_price) {
		this.billdetail_price = billdetail_price;
	}

	public int getBilldetail_quantity() {
		return billdetail_quantity;
	}

	public void setBilldetail_quantity(int billdetail_quantity) {
		this.billdetail_quantity = billdetail_quantity;
	}

	public int getBilldetail_id() {
		return billdetail_id;
	}
}
