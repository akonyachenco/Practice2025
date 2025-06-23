package com.example.package_tracking.service.impl;

import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.dto.mapping.UserMapping;
import com.example.package_tracking.model.User;
import com.example.package_tracking.repository.UserRepository;
import com.example.package_tracking.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapping userMapping;


    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream().map(userMapping::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(userMapping::toDto);
    }

    @Transactional
    public UserDto createUser(UserDto user) {
        User newUser = userMapping.toUser(user);
        userRepository.save(newUser);
        return user;
    }

    @Transactional
    public UserDto updateUser(UserDto user) {
        User newUser = userMapping.toUser(user);
        userRepository.save(newUser);
        return user;
    }

    @Transactional
    public void deleteUser(UserDto user) {
        userRepository.delete(userMapping.toUser(user));
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }
}
