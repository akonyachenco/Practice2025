package com.example.package_tracking.dto.mapping;

import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.dto.UserWithPasswordDto;
import com.example.package_tracking.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Mapper(componentModel = "spring", uses = PackageMapping.class)
public interface UserMapping {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Mapping(target = "userID", source = "userID")
//    @Mapping(target = "passwordHash", ignore = true)
    UserDto toDto(User user);

    @Mapping(target = "userID", source = "userID", ignore = true)
    @Mapping(target = "packages", ignore = true)
    User toEntity(UserDto userDto);

    @Mapping(target = "passwordHash", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    User toEntityWithPassword(UserWithPasswordDto dto);

}
