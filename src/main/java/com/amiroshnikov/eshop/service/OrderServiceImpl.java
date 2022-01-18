package com.amiroshnikov.eshop.service;

import com.amiroshnikov.eshop.dao.OrderRepository;
import com.amiroshnikov.eshop.domain.Order;

import javax.transaction.Transactional;

public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
