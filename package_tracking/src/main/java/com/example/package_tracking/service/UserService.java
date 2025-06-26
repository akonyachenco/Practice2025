package com.example.package_tracking.service;

import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.dto.UserWithPasswordDto;
import com.example.package_tracking.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService{
    List<UserDto> findAll();
    UserDto findById(Long id);
    UserDto findUserByEmail(String email);
    UserDto createUser(UserWithPasswordDto user);
    UserDto updateUser(UserDto user);
//    void deleteUser(UserDto user);
    void deleteUserByEmail(String email);
    void deleteById(Long id);
}
