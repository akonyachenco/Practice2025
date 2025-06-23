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

    @GetMapping("/{id}")
    public Optional<UserDto> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("by-email/{email}")
    public Optional<UserDto> findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @PostMapping("create")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("update")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("delete")
    public void deleteUser(@RequestBody UserDto userDto) {
        userService.deleteUser(userDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @DeleteMapping("delete/by-email/{email}")
    public void deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
    }
}
