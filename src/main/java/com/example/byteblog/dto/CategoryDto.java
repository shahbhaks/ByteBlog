package com.example.byteblog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CategoryDto {


    private Long id;

    @NotEmpty
    @Size(min=4,message ="Min size of category should be 4")
    private String title;

    @NotEmpty
    @Size(min=10,message = "min size of description should be 10 ")
    private String description;
}
