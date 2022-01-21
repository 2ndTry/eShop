package com.amiroshnikov.eshop.service;

import com.amiroshnikov.eshop.dao.ProductRepository;
import com.amiroshnikov.eshop.domain.Product;
import com.amiroshnikov.eshop.dto.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserService userService;
    @Mock
    private BucketService bucketService;
    @Mock
    private SimpMessagingTemplate template;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository, userService, bucketService, template);
    }

    @Test
    void getById() {

        Product product = Product.builder()
                .id(10L)
                .title("Product")
                .price(BigDecimal.valueOf(10.5))
                .build();

        Mockito.when(productRepository.findById(Mockito.eq(10L))).thenReturn(Optional.of(product));

        ProductDto productDto = productService.getById(10L);

        Assertions.assertNotNull(productDto);
        Assertions.assertEquals(product.getId(), productDto.getId());
        Assertions.assertEquals(product.getTitle(), productDto.getTitle());
        Assertions.assertEquals(product.getPrice(), productDto.getPrice());
    }
}