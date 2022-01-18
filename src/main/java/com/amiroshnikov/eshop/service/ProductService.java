package com.amiroshnikov.eshop.service;

import com.amiroshnikov.eshop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    void addToUserBucket(Long productId, String username);
    void addProduct(ProductDto productDto);
    ProductDto getById(Long id);
}
