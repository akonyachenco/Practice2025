package com.example.package_tracking.controller;

import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> findAllUsers() {
        log.info("Find all users");
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserDto> findById(@PathVariable Long id) {
        log.info("Find user by id: {}", id);
        return userService.findById(id);
    }

    @GetMapping("by-email/{email}")
    public Optional<UserDto> findUserByEmail(@PathVariable String email) {
        log.info("Find user by email: {}", email);
        return userService.findUserByEmail(email);
    }

    @PostMapping("create")
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        log.info("Create user: {}", userDto.getEmail());
        return userService.createUser(userDto);
    }

    @PutMapping("update")
    public UserDto updateUser(@RequestBody @Valid UserDto userDto) {
        log.info("Update user with id: {}", userDto.getUserID());
        return userService.updateUser(userDto);
    }

    @DeleteMapping("delete")
    public void deleteUser(@RequestBody @Valid UserDto userDto) {
        log.info("Delete user with id: {}", userDto.getUserID());
        userService.deleteUser(userDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        log.info("Delete user by id: {}", id);
        userService.deleteById(id);
    }

    @DeleteMapping("delete/by-email/{email}")
    public void deleteUserByEmail(@PathVariable String email) {
        log.info("Delete user by email: {}", email);
        userService.deleteUserByEmail(email);
    }
}
