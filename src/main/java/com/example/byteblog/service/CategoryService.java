package com.example.byteblog.service;

import com.example.byteblog.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    //Create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto  updateCategory(CategoryDto categoryDto, Long categoryId);

    //get
    CategoryDto  getCategoryById(Long categoryId);

    //get all
    List<CategoryDto> getAllCategories();

    //delete
    void deleteCategory(Long categoryId);

}
