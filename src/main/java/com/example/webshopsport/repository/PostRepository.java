package com.example.webshopsport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.webshopsport.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	
	@Query("SELECT p FROM Post p WHERE p.postsStatus = ?1")
	public List<Post> getAllPostByStaus(int status);
	
	@Query("SELECT p FROM Post p WHERE p.postsTitle LIKE %?1% AND p.postsStatus = ?2")
	public List<Post> getAllPostByName(String name, int status);
	
	@Query("SELECT p FROM Post p WHERE p.postsId = ?1")
	public Post findPostByPostId(int id);
	
	@Query("SELECT p FROM Post p WHERE p.postsTitle = ?1 AND p.postsStatus = ?2")
	public Post findPostByPostName(String name, int status);

}
