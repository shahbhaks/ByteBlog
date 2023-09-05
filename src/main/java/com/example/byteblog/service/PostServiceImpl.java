package com.example.byteblog.service;

import com.example.byteblog.dto.PostDto;
import com.example.byteblog.exception.ResourceNotFoundException;
import com.example.byteblog.model.Category;
import com.example.byteblog.model.Post;
import com.example.byteblog.model.User;
import com.example.byteblog.repository.CategoryRepo;
import com.example.byteblog.repository.PostRepository;
import com.example.byteblog.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, long userId, long categoryId) {
        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with this id :" + userId));
        Category category=categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category not found with this id:" + categoryId));
        Post post=modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setUser(user);
        post.setCategory(category);

        Post newPost=postRepository.save(post);
        return modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
//        postRepository.find
//        return postToDto(updatedPost);
        return null;
    }

    @Override
    public void deletePost(Long postId) {

    }

    @Override
    public PostDto getPostById(Long postId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {
        Category category=categoryRepo.findById(categoryId)
                        .orElseThrow(()->new ResourceNotFoundException("Category not found with this id :" + categoryId));
        List<Post> posts=postRepository.findAllByCategory(category);
        List<PostDto> postDtos=posts.stream().map((post)->modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Long userId) {
        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with this id :" + userId));
        List<Post> posts=postRepository.findAllByUser(user);
        List<PostDto> postDtos=posts.stream().map((post)->modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }

    //Mapping
   public Post dtoToPost(PostDto postDto){
        return modelMapper.map(postDto,Post.class);
    }
    public PostDto postToDto(Post post){
        return modelMapper.map(post,PostDto.class);
    }
}
