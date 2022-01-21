package com.amiroshnikov.eshop.dao;

import com.amiroshnikov.eshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName(String name);
    User findFirstByActiveCode(String activateCode);
}
