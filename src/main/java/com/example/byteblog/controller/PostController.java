package com.example.byteblog.controller;

import com.example.byteblog.dto.ApiResponse;
import com.example.byteblog.dto.PostDto;
import com.example.byteblog.dto.PostResponse;
import com.example.byteblog.model.Post;
import com.example.byteblog.repository.PostRepository;
import com.example.byteblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Long userId,
                                              @PathVariable Long categoryId){

        PostDto createPost=postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Long userId){
        List<PostDto> posts=postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

    }

    //get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Long categoryId){
        List<PostDto> posts=postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

    }

    //get all posts(Implement paging)
    @GetMapping("/posts")
    //pagenumber starts from 0
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize){
        PostResponse getPosts=postService.getAllPosts(pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(getPosts, HttpStatus.OK);
    }

    //get a post
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
        PostDto postDto=postService.getPostById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post is successfully deleted !!", true), OK);
    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Long postId){
        PostDto updatePost=postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,  HttpStatus.OK);
    }

}
