package com.ProjectAssignment.services.impl;

import com.ProjectAssignment.dtos.CategoryDto;
import com.ProjectAssignment.entities.Category;
import com.ProjectAssignment.repositories.CategoryRepository;
import com.ProjectAssignment.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        String categoryId = UUID.randomUUID().toString();
        categoryDto.setCategoryId(categoryId);
        Category category = dtoToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return entityToDto(savedCategory);

    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found with given id !!"));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory = categoryRepository.save(category);
        return entityToDto(updatedCategory);
    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found with given id !!"));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found with given id !!"));
        return entityToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories(int pageNumber,int pageSize) {
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Page<Category> categoriesPage=categoryRepository.findAll(pageable);
        List<Category>categories=categoriesPage.getContent();
       List<CategoryDto>categoryDtoList= categories.stream().map(Category->entityToDto(Category)).collect(Collectors.toList());
        return categoryDtoList;
    }

    private CategoryDto entityToDto(Category savedCategory)
    {
        return mapper.map(savedCategory,CategoryDto.class);
    }

    private Category dtoToEntity(CategoryDto savedCategory)
    {
        return mapper.map(savedCategory,Category.class);
    }

}
