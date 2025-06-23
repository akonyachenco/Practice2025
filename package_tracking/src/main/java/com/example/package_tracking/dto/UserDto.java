package com.example.package_tracking.dto;

import lombok.Data;


import java.util.List;

@Data
public class UserDto {
    private Long userID;
    private String name;
    private String passwordHash;
    private String email;
    private String phone;
    private List<PackageDto> packages;
}
