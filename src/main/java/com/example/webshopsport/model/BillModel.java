package com.example.webshopsport.model;

import java.util.Date;

public class BillModel {
	
	private int bill_id;
	private int user_id;
	private double bill_total;
	private String bill_payment;
	private String bill_address_ship;
	private Date bill_date;
	private int bill_status;
	
	public BillModel() {
		
	}
	
	public BillModel(int bill_id, int user_id, double bill_total, String bill_payment, String bill_address_ship,
			Date bill_date, int bill_status) {
		super();
		this.bill_id = bill_id;
		this.user_id = user_id;
		this.bill_total = bill_total;
		this.bill_payment = bill_payment;
		this.bill_address_ship = bill_address_ship;
		this.bill_date = bill_date;
		this.bill_status = bill_status;
	}


	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public double getBill_total() {
		return bill_total;
	}

	public void setBill_total(double bill_total) {
		this.bill_total = bill_total;
	}

	public String getBill_payment() {
		return bill_payment;
	}

	public void setBill_payment(String bill_payment) {
		this.bill_payment = bill_payment;
	}

	public String getBill_address_ship() {
		return bill_address_ship;
	}

	public void setBill_address_ship(String bill_address_ship) {
		this.bill_address_ship = bill_address_ship;
	}

	public Date getBill_date() {
		return bill_date;
	}

	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}

	public int getBill_status() {
		return bill_status;
	}

	public void setBill_status(int bill_status) {
		this.bill_status = bill_status;
	}

	public int getBill_id() {
		return bill_id;
	}

	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
}
