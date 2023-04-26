package com.example.webshopsport.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webshopsport.model.PostsModel;
import com.example.webshopsport.model.ResponseObject;
import com.example.webshopsport.service.IPostService;

@RestController
@CrossOrigin
public class PostController {
	
	@Autowired
	IPostService postService;
	
	@GetMapping("/api/getPostById/{id}")
	public ResponseEntity<ResponseObject> getPost(@PathVariable("id") int id) {
		PostsModel postModel = postService.getByID(id);
		if(postModel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Get  Post Success", postModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "No Post To Get", null));
	}
	
	@GetMapping("/api/getallpost/{status}")
	public ResponseEntity<ResponseObject> getAllPost(@PathVariable("status") int status) {
		ArrayList<PostsModel> listPostModel = postService.getListPostByStatus(status);
		if(listPostModel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Get List Post Success", listPostModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Don't Post To Get", null));
	}
	
	@GetMapping("/api/searchbypostname/{name}")
	public ResponseEntity<ResponseObject> findPostByName(@PathVariable("name") String name) {
		ArrayList<PostsModel> listPost = postService.findPostByName(name);
		if(listPost != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Get List Post Success", listPost));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Don't Post To Get", null));
	}
	
	@PostMapping("/admin/createpost")
	public ResponseEntity<ResponseObject> createPost(@RequestBody PostsModel postModel) {
		PostsModel postData = postService.createPost(postModel);
		if(postData != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Create Post Success", postData));
		}
;		return ResponseEntity.status(HttpStatus.OK).body(
		new ResponseObject(200, "Create Post Failed, Post Is Exists", null));
	}
	
	@PutMapping("/admin/updatepost")
	public ResponseEntity<ResponseObject> updatePost(@RequestBody PostsModel postModel) {
		PostsModel result = postService.updatePost(postModel);
		if(result != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Update Post Success", result));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Update Post Failed, Don't Found Post", null));
	}

}
