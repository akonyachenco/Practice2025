package com.example.package_tracking.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;


import java.util.List;

@Data
public class UserDto {
    private Long userID;
    private String name;
    private String passwordHash;
    @Email
    private String email;
    private String phone;
    private List<PackageDto> packages;
}
