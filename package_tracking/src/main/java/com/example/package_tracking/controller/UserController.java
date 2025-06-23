package com.example.package_tracking.controller;

import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{email}")
    public Optional<UserDto> findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @PostMapping("create-user")
    public UserDto createUser(@RequestBody UserDto user) {
        return userService.createUser(user);
    }

    @PutMapping("update-user")
    public UserDto updateUser(@RequestBody UserDto user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("delete-user")
    public void deleteUser(@RequestBody UserDto user) {
        userService.deleteUser(user);
    }

    @DeleteMapping("delete-user/{email}")
    public void deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
    }
}
