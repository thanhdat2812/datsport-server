package com.example.webshopsport.model;

public class AuthenticationResponse {

	private final String token;
	private final int id;
	private final String image;
	private final String userName;
	private final String email;
	private final int role;
	
	


	public AuthenticationResponse(String token, int id, String image, String userName, String email, int role) {
		
		this.token = token;
		this.id = id;
		this.image = image;
		this.userName = userName;
		this.email = email;
		this.role = role;
	}


	public String getImage() {
		return image;
	}


	public int getRole() {
		return role;
	}

	public String getToken() {
		return token;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}
}