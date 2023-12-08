package com.example.byteblog.service;

import com.example.byteblog.dto.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Long postId);
    void deleteComment(Long commentId);
}
