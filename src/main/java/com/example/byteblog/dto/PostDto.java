package com.example.byteblog.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Data
public class PostDto {
    private Long postId;

    private String title;

    private String content;

   // private Date addedDate;

    private String imageName;

    private CategoryDto category;

    private UserDto user;

    private List<CommentDto> comments;

}
