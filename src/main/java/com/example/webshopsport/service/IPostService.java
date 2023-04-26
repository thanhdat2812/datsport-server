package com.example.webshopsport.service;

import java.util.ArrayList;

import com.example.webshopsport.model.PostsModel;

public interface IPostService {
	
	public ArrayList<PostsModel> getListPostByStatus(int status);
	public ArrayList<PostsModel> findPostByName(String name);
	public PostsModel createPost(PostsModel postModel);
	public PostsModel updatePost(PostsModel postModel);
	public PostsModel getByID(int id);
}
