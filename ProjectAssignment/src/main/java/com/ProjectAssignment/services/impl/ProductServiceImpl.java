package com.ProjectAssignment.services.impl;

import com.ProjectAssignment.dtos.CategoryDto;
import com.ProjectAssignment.dtos.ProductDto;
import com.ProjectAssignment.entities.Category;
import com.ProjectAssignment.entities.Product;
import com.ProjectAssignment.repositories.CategoryRepository;
import com.ProjectAssignment.repositories.ProductRepository;
import com.ProjectAssignment.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;



    @Override
    public ProductDto create(ProductDto productDto) {
        Product product=modelMapper.map(productDto, Product.class);
        String productId= UUID.randomUUID().toString();
        product.setP_id(productId);
        Product savedProduct=productRepository.save(product);
        return entityToDto(savedProduct);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("Given productId is not found!!"));
        product.setProductName(productDto.getProductName());
        product.setP_description(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        Product savedProduct=productRepository.save(product);
        return entityToDto(savedProduct);
    }

    @Override
    public void delete(String productId) {

        Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("Given ProductId is not found!!"));
        productRepository.delete(product);
    }

    @Override
    public ProductDto get(String productId) {
        Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("Given ProductId is not found!!"));
        return entityToDto(product);
    }

    @Override
    public ProductDto createWithCategory(String categoryId,ProductDto productDto) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Given categoryId is not found"));
        Product product=modelMapper.map(productDto,Product.class);
        String productId=UUID.randomUUID().toString();
        product.setP_id(productId);
        product.setCategory(category);
        Product savedProduct=productRepository.save(product);
        return entityToDto(savedProduct);
    }

    @Override
    public List<ProductDto> getAllProducts(int pageNum,int pageSize) {

        Pageable pageable= PageRequest.of(pageNum,pageSize);
        Page<Product> productsPage=productRepository.findAll(pageable);
        List<Product>productList=productsPage.getContent();
        List<ProductDto>productDtoList=productList.stream().map(product -> entityToDto(product)).collect(Collectors.toList());
        return productDtoList;
    }

    // convertion of entity to dto
    private ProductDto entityToDto(Product savedProduct)
    {
        return modelMapper.map(savedProduct,ProductDto.class);
    }

    // convertion of dto to entity
    private Product dtoToEntity(ProductDto savedProductDto)
    {
        return modelMapper.map(savedProductDto,Product.class);
    }

}
