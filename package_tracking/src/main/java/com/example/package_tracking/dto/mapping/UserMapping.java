package com.example.package_tracking.dto.mapping;

import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring", uses = PackageMapping.class)
public interface UserMapping {

    @Mapping(target = "userID", source = "userID")

    UserDto toDto(User user);

    @Mapping(target = "userID", source = "userID", ignore = true)
    @Mapping(target = "packages", ignore = true)
    User toEntity(UserDto userDto);
}
