package com.amiroshnikov.eshop.controller;

import com.amiroshnikov.eshop.dto.ProductDto;
import com.amiroshnikov.eshop.service.ProductService;
import com.amiroshnikov.eshop.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductRestController.class)
class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;
    @MockBean
    private UserService userService;

    private BigDecimal price = BigDecimal.valueOf(888.88);

    private ProductDto dto = new ProductDto(998L, "Test product 998", price);

    @BeforeEach
    void setUp() {
        given(productService.getById(dto.getId())).willReturn(dto);
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/products/{id}", dto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(998)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test product 998")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(888.88)));
    }
}