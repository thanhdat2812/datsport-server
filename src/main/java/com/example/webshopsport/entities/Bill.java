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
 * The persistent class for the bill database table.
 * 
 */
@Entity
@Cacheable(false)
@NamedQuery(name="Bill.findAll", query="SELECT b FROM Bill b")
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bill_id")
	private int billId;

	@Column(name="bill_address_ship")
	private String billAddressShip;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="bill_date")
	private Date billDate;

	@Column(name="bill_payment")
	private String billPayment;

	@Column(name="bill_status")
	private int billStatus;

	@Column(name="bill_total")
	private double billTotal;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="user_id")
	private Account account;

	//bi-directional many-to-one association to Billdetail
	@OneToMany(mappedBy="bill")
//	@JsonBackReference
	@JsonIgnore
	private List<Billdetail> billdetails;

	public Bill() {
	}

	public int getBillId() {
		return this.billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public String getBillAddressShip() {
		return this.billAddressShip;
	}

	public void setBillAddressShip(String billAddressShip) {
		this.billAddressShip = billAddressShip;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getBillPayment() {
		return this.billPayment;
	}

	public void setBillPayment(String billPayment) {
		this.billPayment = billPayment;
	}

	public int getBillStatus() {
		return this.billStatus;
	}

	public void setBillStatus(int billStatus) {
		this.billStatus = billStatus;
	}

	public double getBillTotal() {
		return this.billTotal;
	}

	public void setBillTotal(double billTotal) {
		this.billTotal = billTotal;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Billdetail> getBilldetails() {
		return this.billdetails;
	}

	public void setBilldetails(List<Billdetail> billdetails) {
		this.billdetails = billdetails;
	}

	public Billdetail addBilldetail(Billdetail billdetail) {
		getBilldetails().add(billdetail);
		billdetail.setBill(this);

		return billdetail;
	}

	public Billdetail removeBilldetail(Billdetail billdetail) {
		getBilldetails().remove(billdetail);
		billdetail.setBill(null);

		return billdetail;
	}

}