package com.amiroshnikov.eshop.dto;

import com.amiroshnikov.eshop.domain.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderIntegrationDto {
    private Long orderId;
    private String username;
    private String address;
    private List<OrdersDetailDto> details;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrdersDetailDto {
        private String product;
        private Double price;
        private Double amount;
        private Double sum;

        public OrdersDetailDto(OrderDetails details) {
            this.product = details.getProduct().getTitle();
            this.price = details.getPrice().doubleValue();
            this.amount = details.getAmount().doubleValue();
            this.sum = details.getPrice().multiply(details.getAmount()).doubleValue();
        }
    }
}
