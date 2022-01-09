package com.amiroshnikov.eshop.endpoint;

import com.amiroshnikov.eshop.dto.ProductDto;
import com.amiroshnikov.eshop.service.ProductService;
import com.amiroshnikov.eshop.ws.products.GetProductsRequest;
import com.amiroshnikov.eshop.ws.products.GetProductsResponse;
import com.amiroshnikov.eshop.ws.products.ProductsWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductsEndpoint {

    public static final String NAMESPACE_URL = "http://amiroshnikov.com/eshop/ws/products";

    private ProductService productService;

    @Autowired
    public ProductsEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProductWS(@RequestPayload GetProductsRequest request) {
        GetProductsResponse response = new GetProductsResponse();
        productService.getAll()
                .forEach(productDto -> response.getProducts().add(createProductsWS(productDto)));
        return response;
    }

    private ProductsWS createProductsWS(ProductDto productDto) {
        ProductsWS ws = new ProductsWS();
        ws.setId(productDto.getId());
        ws.setPrice(Double.parseDouble(productDto.getPrice().toString()));
        ws.setTitle(productDto.getTitle());
        return ws;
    }
}
