package com.amiroshnikov.eshop.controller;

import com.amiroshnikov.eshop.dto.ProductDto;
import com.amiroshnikov.eshop.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }
}
