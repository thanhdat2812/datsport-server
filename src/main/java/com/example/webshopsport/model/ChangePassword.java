package com.example.webshopsport.model;

public class ChangePassword {
	
	public String username;
	public String password;
	public String newPassword;
	
	public ChangePassword() {
	
	}

	@Override
	public String toString() {
		return "ChangePassword [username=" + username + ", password=" + password + ", newPassword=" + newPassword + "]";
	}

	public ChangePassword(String username, String password, String newPassword) {
		this.username = username;
		this.password = password;
		this.newPassword = newPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
