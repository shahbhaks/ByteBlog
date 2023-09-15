package com.example.byteblog.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;
    private Long totalElements;
    private int totalPages;

    private boolean lastPage;
}
