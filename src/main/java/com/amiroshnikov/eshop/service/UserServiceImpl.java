package com.amiroshnikov.eshop.service;

import com.amiroshnikov.eshop.dao.UserRepository;
import com.amiroshnikov.eshop.domain.Role;
import com.amiroshnikov.eshop.domain.User;
import com.amiroshnikov.eshop.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(UserDto userDto) {
        if (!Objects.equals(userDto.getPassword(), userDto.getMatchingPassword())) {
            throw new RuntimeException("Password is not confirm!");
        }
        User user = User.builder()
                .name(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .role(Role.CLIENT)
                .build();
        userRepository.save(user);
        return true;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with username \"%s\" not found!", username));
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                roles
        );
    }

    @Override
    public User findByName(String name) {
        return userRepository.findFirstByName(name);
    }

    @Override
    public void updateProfile(UserDto userDto) {
        User savedUser = userRepository.findFirstByName(userDto.getUsername());
        if (savedUser == null) {
            throw new RuntimeException(String.format("User with name \"%s\" not found", userDto.getUsername()));
        }

        boolean isChanged = false;
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            savedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
            isChanged = true;
        }

        if (!Objects.equals(userDto.getEmail(), savedUser.getEmail())) {
            savedUser.setEmail(userDto.getEmail());
            isChanged = true;
        }

        if (isChanged) {
            userRepository.save(savedUser);
        }
    }

    private UserDto toDto(User user) {
        return UserDto.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }
}
