package com.example.package_tracking.service;

import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService{
    List<UserDto> findAll();
    Optional<UserDto> findById(Long id);
    Optional<UserDto> findUserByEmail(String email);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user);
    void deleteUser(UserDto user);
    void deleteUserByEmail(String email);
    void deleteById(Long id);
}
