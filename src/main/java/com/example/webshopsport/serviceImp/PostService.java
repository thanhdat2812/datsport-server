package com.example.webshopsport.serviceImp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.webshopsport.entities.Post;
import com.example.webshopsport.model.PostsModel;
import com.example.webshopsport.repository.PostRepository;
import com.example.webshopsport.service.IPostService;

@Service
public class PostService implements IPostService {
	
	@Autowired
	PostRepository postRepository;

	@Override
	public ArrayList<PostsModel> getListPostByStatus(int status) {
		ArrayList<Post> listPost = new ArrayList<>();
		ArrayList<PostsModel> listPostModel = new ArrayList<>();
		if(status == -1) {
			listPost = (ArrayList<Post>) postRepository.findAll();
		}
		else {
			listPost = (ArrayList<Post>) postRepository.getAllPostByStaus(status);
		}
		for (Post post : listPost) {
			listPostModel.add(convertToDTO(post));
		}
		return listPostModel;
	}
	
	@Override
	public ArrayList<PostsModel> findPostByName(String name) {
		ArrayList<Post> listPosts = (ArrayList<Post>) postRepository.getAllPostByName(name, 1);
		ArrayList<PostsModel> listPostModel = new ArrayList<>();
		if(listPosts != null) {
			for (Post post : listPosts) {
				listPostModel.add(convertToDTO(post));
			}
		}
		return listPostModel;
	}

	@Override
	public PostsModel createPost(PostsModel postModel) {
		Post checkPost = postRepository.findPostByPostName(postModel.getPosts_title(), 1);
		if(checkPost == null) {
			Post post = convertToEntity(postModel);
			postRepository.save(post);
			Post data = postRepository.findPostByPostName(post.getPostsTitle(), 1);
			return convertToDTO(data);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public PostsModel updatePost(PostsModel postModel) {
		Post checkPost = postRepository.findPostByPostId(postModel.getPosts_id());
		if(checkPost == null) {
			return null;
		}
		Post post = convertToEntity(postModel);
		post.setPostsId(postModel.getPosts_id());
//		post.setPostsContent(postModel.getPosts_content());
		postRepository.save(post);
		Post result = postRepository.findPostByPostId(postModel.getPosts_id());
		return convertToDTO(result);
	}
	
	@Override
	public PostsModel getByID(int id) {
		Post checkPost = postRepository.findPostByPostId(id);
		if(checkPost == null) {
			return null;
		}
		return convertToDTO(checkPost);
	}
	
	public Post convertToEntity(PostsModel postsModel) {
		Post post = new Post();
		post.setPostsId(postsModel.getPosts_id());
		post.setPostsTitle(postsModel.getPosts_title());
		post.setPostsContent(postsModel.getPosts_content());
		post.setPostsImage(postsModel.getPosts_image());
		post.setPostsCreateDate(postsModel.getPosts_create_date());
		post.setPostsCreateUser(postsModel.getPosts_create_user());
		post.setPostsUpdateDate(postsModel.getPosts_update_date());
		post.setPostsUpdateUser(postsModel.getPosts_update_user());
		post.setPostsStatus(postsModel.getPosts_status());
		return post;
	}
	
	public PostsModel convertToDTO(Post post) {
		PostsModel postModel = new PostsModel();
		postModel.setPosts_id(post.getPostsId());
		postModel.setPosts_title(post.getPostsTitle());
		postModel.setPosts_content(post.getPostsContent());
		postModel.setPosts_image(post.getPostsImage());
		postModel.setPosts_create_date(post.getPostsCreateDate());
		postModel.setPosts_create_user(post.getPostsCreateUser());
		postModel.setPosts_update_date(post.getPostsUpdateDate());
		postModel.setPosts_update_user(post.getPostsUpdateUser());
		postModel.setPosts_status(post.getPostsStatus());
		return postModel;
	}

	

}
