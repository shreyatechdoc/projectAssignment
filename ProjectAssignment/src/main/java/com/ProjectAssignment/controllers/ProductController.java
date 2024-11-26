package com.ProjectAssignment.controllers;

import com.ProjectAssignment.dtos.ApiResponseMessage;
import com.ProjectAssignment.dtos.ProductDto;
import com.ProjectAssignment.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto productDto1 = productService.create(productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId, @RequestBody ProductDto productDto)
    {
        ProductDto productDto1 = productService.update(productDto, productId);
        return new ResponseEntity<>(productDto1,HttpStatus.OK);

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseMessage>deleteProduct(@PathVariable String productId)
    {
        productService.delete(productId);
        ApiResponseMessage apiResponseMessage=ApiResponseMessage.builder().message("product deleted successfully !!")
                .success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(apiResponseMessage,HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto>getProduct(@PathVariable String productId)
    {
        ProductDto getProduct=productService.get(productId);
        return new ResponseEntity<>(getProduct,HttpStatus.OK);
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<ProductDto>CreateWithCategory(@PathVariable String categoryId,@RequestBody ProductDto productDto)
    {
        ProductDto productDto1=productService.createWithCategory(categoryId,productDto);
        return new ResponseEntity<>(productDto1,HttpStatus.CREATED) ;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>>getAllProducts(@RequestParam(value = "pageNum",defaultValue = "0",required = false) int pageNum,
                                                          @RequestParam(value = "pageSize",defaultValue ="10",required = false) int pageSize)
    {
        List<ProductDto>productDtoList=productService.getAllProducts( pageNum, pageSize);
        return new ResponseEntity<>(productDtoList,HttpStatus.OK);
    }

}