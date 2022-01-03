package com.amiroshnikov.eshop.dao;

import com.amiroshnikov.eshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
