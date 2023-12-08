package com.example.byteblog.service;

import com.example.byteblog.dto.CommentDto;
import com.example.byteblog.exception.ResourceNotFoundException;
import com.example.byteblog.model.Comment;
import com.example.byteblog.model.Post;
import com.example.byteblog.repository.CommentRepo;
import com.example.byteblog.repository.PostRepository;
import jakarta.persistence.MappedSuperclass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post not found with this id :" + postId));
        Comment comment=modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);

       Comment savedComment= commentRepo.save(comment);
        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment=commentRepo.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment not found with this id" + commentId ));
        commentRepo.delete(comment);
    }
}
