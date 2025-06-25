package com.example.package_tracking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import java.util.List;

@Data
public class UserDto {
    private Long userID;
    @NotBlank
    private String name;
    @NotBlank
    private String passwordHash;
    @NotBlank
    @Email
    private String email;
    private String phone;
    private List<PackageDto> packages;
}
