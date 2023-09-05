package com.example.byteblog.service;

import com.example.byteblog.dto.PostDto;
import com.example.byteblog.model.Category;
import com.example.byteblog.model.User;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto, long userId, long categoryId);

    //update
    PostDto updatePost(PostDto postDto, Long postId);

    //delete
    void deletePost(Long postId);

    //get single post
    PostDto getPostById(Long postId);

    //get all
    List<PostDto> getAllPosts();

    //get all post by category
    List<PostDto> getPostsByCategory(Long categoryId);

    //get all posts by user
    List<PostDto> getPostsByUser(Long userId);

    //search posts
    List<PostDto> searchPosts(String keyword);

}
