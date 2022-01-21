package com.amiroshnikov.eshop.service;

import com.amiroshnikov.eshop.domain.User;
import com.amiroshnikov.eshop.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDto userDto);
    void save(User user);
    List<UserDto> getAll();
    User findByName(String name);
    void updateProfile(UserDto userDto);
    boolean activateUser(String activateCode);
}
