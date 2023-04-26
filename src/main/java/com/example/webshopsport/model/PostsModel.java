package com.example.webshopsport.model;

import java.util.Date;

public class PostsModel {
	
	private int posts_id;
	private String posts_title;
	private String posts_content;
	private String posts_image;
	private Date posts_create_date;
	private String posts_create_user;
	private Date posts_update_date;
	private String posts_update_user;
	private int posts_status;
	
	public PostsModel() {
		
	}

	public PostsModel(int posts_id, String posts_title, String posts_content, String posts_image,
			Date posts_create_date, String posts_create_user, Date posts_update_date, String posts_update_user,
			int posts_status) {
		
		this.posts_id = posts_id;
		this.posts_title = posts_title;
		this.posts_content = posts_content;
		this.posts_image = posts_image;
		this.posts_create_date = posts_create_date;
		this.posts_create_user = posts_create_user;
		this.posts_update_date = posts_update_date;
		this.posts_update_user = posts_update_user;
		this.posts_status = posts_status;
	}

	public int getPosts_id() {
		return posts_id;
	}

	public void setPosts_id(int posts_id) {
		this.posts_id = posts_id;
	}

	public String getPosts_title() {
		return posts_title;
	}

	public void setPosts_title(String posts_title) {
		this.posts_title = posts_title;
	}

	public String getPosts_content() {
		return posts_content;
	}

	public void setPosts_content(String posts_content) {
		this.posts_content = posts_content;
	}

	public String getPosts_image() {
		return posts_image;
	}

	public void setPosts_image(String posts_image) {
		this.posts_image = posts_image;
	}

	public Date getPosts_create_date() {
		return posts_create_date;
	}

	public void setPosts_create_date(Date posts_create_date) {
		this.posts_create_date = posts_create_date;
	}

	public String getPosts_create_user() {
		return posts_create_user;
	}

	public void setPosts_create_user(String posts_create_user) {
		this.posts_create_user = posts_create_user;
	}

	public Date getPosts_update_date() {
		return posts_update_date;
	}

	public void setPosts_update_date(Date posts_update_date) {
		this.posts_update_date = posts_update_date;
	}

	public String getPosts_update_user() {
		return posts_update_user;
	}

	public void setPosts_update_user(String posts_update_user) {
		this.posts_update_user = posts_update_user;
	}

	public int getPosts_status() {
		return posts_status;
	}

	public void setPosts_status(int posts_status) {
		this.posts_status = posts_status;
	}

	
}
