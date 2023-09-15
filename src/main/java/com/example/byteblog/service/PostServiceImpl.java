package com.example.byteblog.service;

import com.example.byteblog.dto.PostDto;
import com.example.byteblog.dto.PostResponse;
import com.example.byteblog.exception.ResourceNotFoundException;
import com.example.byteblog.model.Category;
import com.example.byteblog.model.Post;
import com.example.byteblog.model.User;
import com.example.byteblog.repository.CategoryRepo;
import com.example.byteblog.repository.PostRepository;
import com.example.byteblog.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Post post=dtoToPost(postDto);
        post.setImageName("default.png");
        post.setUser(user);
        post.setCategory(category);

        Post newPost=postRepository.save(post);
        return postToDto(newPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new RuntimeException("Post not found with this id :" + postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost=postRepository.save(post);

        return postToDto(updatedPost);
    }

    @Override
    public void deletePost(Long postId) {
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post not found with this id :" + postId));
        postRepository.delete(post);

    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post= postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post not found with this id : " + postId));
        return postToDto(post);
    }

    //Implementented paging & sorting
    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p= PageRequest.of(pageNumber,pageSize, sort);

        Page<Post> pagePost =postRepository.findAll(p);
        List<Post> allPosts=pagePost.getContent();

        List<PostDto> postDtos=allPosts.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {
        Category cat=categoryRepo.findById(categoryId)
                        .orElseThrow(()->new ResourceNotFoundException("Category not found with this id :" + categoryId));
        List<Post> posts=postRepository.findByCategory(cat);
        List<PostDto> postDtos=posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Long userId) {
        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with this id :" + userId));
        List<Post> posts=postRepository.findByUser(user);
        List<PostDto> postDtos=posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
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
