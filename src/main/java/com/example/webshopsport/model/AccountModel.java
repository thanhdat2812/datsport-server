package com.example.webshopsport.model;

public class AccountModel {
	
	private int id;
	private String username;
	private String userfullname;
	private String email;
	private String password;
	private byte gender;
	private String phone;
	private String address;
	private String image;
	private int role;
	private int status;
	
	public AccountModel() {

	}

	

	public AccountModel(int id, String username,String userfullname, String email, String password, byte gender, String phone,
			String address, String image, int role, int status) {
		super();
		this.id = id;
		this.username = username;
		this.userfullname=userfullname;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.image = image;
		this.role = role;
		this.status = status;
	}



	@Override
	public String toString() {
		return "AccountModel [id=" + id + ", username=" + username + ", userfullname=" + userfullname + ", email="
				+ email + ", password=" + password + ", gender=" + gender + ", phone=" + phone + ", address=" + address
				+ ", image=" + image + ", role=" + role + ", status=" + status + "]";
	}



	public String getUserfullname() {
		return userfullname;
	}



	public void setUserfullname(String userfullname) {
		this.userfullname = userfullname;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getGender() {
		return gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
