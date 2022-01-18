package com.amiroshnikov.eshop.controller;

import com.amiroshnikov.eshop.dto.ProductDto;
import com.amiroshnikov.eshop.service.ProductService;
import com.amiroshnikov.eshop.service.SessionObjectHolder;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;
    @MockBean
    private UserService userService;
    @MockBean
    private SessionObjectHolder objectHolder;

    private BigDecimal price1 = BigDecimal.valueOf(888.88);
    private BigDecimal price2 = BigDecimal.valueOf(999.99);

    private ProductDto dto1 = new ProductDto(998L, "Test product 998", price1);
    private ProductDto dto2 = new ProductDto(999L, "Test product 999", price2);

    @BeforeEach
    void setUp() {
        given(productService.getAll()).willReturn(Arrays.asList(dto1, dto2));
    }

    @Test
    void checkList() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content().string(Matchers.containsString("<td>" + dto1.getTitle() + "</td>")))
                .andExpect(MockMvcResultMatchers
                        .content().string(Matchers.containsString("<td>" + dto2.getTitle() + "</td>")));
    }
}