package com.example.package_tracking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserWithPasswordDto extends UserDto {
    @NotBlank
    private String password;
}
