package com.example.webshopsport.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the posts database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="posts")
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="posts_id")
	private int postsId;

	@Column(name="posts_content", columnDefinition = "text")
	private String postsContent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="posts_create_date")
	private Date postsCreateDate;

	@Column(name="posts_create_user")
	private String postsCreateUser;

	@Column(name="posts_image")
	private String postsImage;

	@Column(name="posts_status")
	private int postsStatus;

	@Column(name="posts_title")
	private String postsTitle;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="posts_update_date")
	private Date postsUpdateDate;

	@Column(name="posts_update_user")
	private String postsUpdateUser;

	public Post() {
	}

	public int getPostsId() {
		return this.postsId;
	}

	public void setPostsId(int postsId) {
		this.postsId = postsId;
	}

	public String getPostsContent() {
		return this.postsContent;
	}

	public void setPostsContent(String postsContent) {
		this.postsContent = postsContent;
	}

	public Date getPostsCreateDate() {
		return this.postsCreateDate;
	}

	public void setPostsCreateDate(Date postsCreateDate) {
		this.postsCreateDate = postsCreateDate;
	}

	public String getPostsCreateUser() {
		return this.postsCreateUser;
	}

	public void setPostsCreateUser(String postsCreateUser) {
		this.postsCreateUser = postsCreateUser;
	}

	public String getPostsImage() {
		return this.postsImage;
	}

	public void setPostsImage(String postsImage) {
		this.postsImage = postsImage;
	}

	public int getPostsStatus() {
		return this.postsStatus;
	}

	public void setPostsStatus(int postsStatus) {
		this.postsStatus = postsStatus;
	}

	public String getPostsTitle() {
		return this.postsTitle;
	}

	public void setPostsTitle(String postsTitle) {
		this.postsTitle = postsTitle;
	}

	public Date getPostsUpdateDate() {
		return this.postsUpdateDate;
	}

	public void setPostsUpdateDate(Date postsUpdateDate) {
		this.postsUpdateDate = postsUpdateDate;
	}

	public String getPostsUpdateUser() {
		return this.postsUpdateUser;
	}

	public void setPostsUpdateUser(String postsUpdateUser) {
		this.postsUpdateUser = postsUpdateUser;
	}

}