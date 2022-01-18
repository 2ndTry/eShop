package com.amiroshnikov.eshop.dao;

import com.amiroshnikov.eshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
