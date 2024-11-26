package com.ProjectAssignment.services;

import com.ProjectAssignment.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto categoryDto);
    CategoryDto update(CategoryDto categoryDto, String categoryId);
    void delete(String categoryId);
    CategoryDto get(String categoryId);
    List<CategoryDto>getAllCategories(int pageNumber,int pageSize);


}
