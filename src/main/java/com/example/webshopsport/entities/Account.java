package com.example.webshopsport.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@Cacheable(false)
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	@Column(name="user_address")
	private String userAddress;

	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_gender")
	private byte userGender;

	@Column(name="user_image")
	private String userImage;

	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_fullname")
	private String userFullname;

	public String getUserFullname() {
		return userFullname;
	}

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	@Column(name="user_pass")
	private String userPass;

	@Column(name="user_phone")
	private String userPhone;

	@Column(name="user_role")
	private int userRole;

	@Column(name="user_status")
	private int userStatus;

	//bi-directional many-to-one association to Bill
	@OneToMany(mappedBy="account")
	@JsonBackReference
	private List<Bill> bills;

	public Account() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public byte getUserGender() {
		return this.userGender;
	}

	public void setUserGender(byte userGender) {
		this.userGender = userGender;
	}

	public String getUserImage() {
		return this.userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public int getUserRole() {
		return this.userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public int getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public List<Bill> getBills() {
		return this.bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public Bill addBill(Bill bill) {
		getBills().add(bill);
		bill.setAccount(this);

		return bill;
	}

	public Bill removeBill(Bill bill) {
		getBills().remove(bill);
		bill.setAccount(null);

		return bill;
	}

}