package com.ProjectAssignment.services;

import com.ProjectAssignment.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto productDto);
    ProductDto update(ProductDto productDto,String productId);
    void delete(String productId);
    ProductDto get(String productId);
    ProductDto createWithCategory(String categoryId,ProductDto productDto);
    List<ProductDto>getAllProducts(int pageNum,int pageSize);
}
