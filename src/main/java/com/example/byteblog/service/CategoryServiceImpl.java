package com.example.byteblog.service;

import com.example.byteblog.dto.CategoryDto;
import com.example.byteblog.exception.ResourceNotFoundException;
import com.example.byteblog.model.Category;
import com.example.byteblog.model.User;
import com.example.byteblog.repository.CategoryRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=dtoToCategory(categoryDto);
        Category addedCategory=categoryRepo.save(category);
        return categoryToDto(addedCategory);

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category=categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category not found with this id : " + categoryId));

        category.setDescription(categoryDto.getDescription());
        category.setTitle(categoryDto.getTitle());

        Category updatedCategory=categoryRepo.save(category);
        return categoryToDto(updatedCategory);


    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category=categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category not found with this id : " + categoryId));
        return categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories=categoryRepo.findAll();
        List<CategoryDto> categoryDtos=categories.stream().map(category->this.categoryToDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category=categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category not found with this id : " + categoryId));
        categoryRepo.delete(category);
    }

    //Mapping
    public Category dtoToCategory(CategoryDto categoryDto){
        return this.modelMapper.map(categoryDto,Category.class);
    }

    public CategoryDto categoryToDto(Category category){
        return this.modelMapper.map(category,CategoryDto.class);
    }
}
