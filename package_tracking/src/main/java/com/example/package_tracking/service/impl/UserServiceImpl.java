package com.example.package_tracking.service.impl;

import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.dto.mapping.UserMapping;
import com.example.package_tracking.exception.EntityNotFoundException;
import com.example.package_tracking.model.User;
import com.example.package_tracking.repository.UserRepository;
import com.example.package_tracking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
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
    public UserDto findById(Long id) {
        return  userRepository.findById(id)
                .map(userMapping::toDto).orElseThrow(() -> {
                    log.warn("User not found with id {}", id);
                    return EntityNotFoundException.create("User not found with id", id);
                        });
    }

    @Transactional(readOnly = true)
    public UserDto findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(userMapping::toDto).orElseThrow(() -> {
                    log.warn("User not found with email {}", email);
                    return EntityNotFoundException.create("User not found with email", email);
                });
    }

    @Transactional
    public UserDto createUser(UserDto user) {
        User newUser = userMapping.toEntity(user);
        return userMapping.toDto( userRepository.save(newUser));
    }

    @Transactional
    public UserDto updateUser(UserDto user) {
        User upUser = userRepository.findById(user.getUserID()).orElseThrow(() -> {
            log.warn("User not found with id {}", user.getUserID());
            return EntityNotFoundException.create("User not found with id", user.getUserID());
        });
        if(user.getEmail() != null)
            upUser.setEmail(user.getEmail());
        if(user.getName() != null)
            upUser.setName(user.getName());
        if(user.getPasswordHash() != null)
            upUser.setPasswordHash(user.getPasswordHash());
        if(user.getPhone() != null)
            upUser.setPhone(user.getPhone());
        return userMapping.toDto(userRepository.save(upUser));
    }

    @Transactional
    public void deleteUser(UserDto user) {
        User delUser = userRepository.findById(user.getUserID()).orElseThrow(() -> {
            log.warn("User not found with id {}", user.getUserID());
            return EntityNotFoundException.create("User not found with id", user.getUserID());
        });
        userRepository.delete(delUser);
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
