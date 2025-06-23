package com.example.package_tracking.dto.mapping;

import com.example.package_tracking.dto.PackageDto;
import com.example.package_tracking.model.Package;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PackageMapping {

    @Mapping(target = "packageID", source = "packageID")
    @Mapping(target = "userID", expression = "java(pkg.getUser().getUserID())")
    PackageDto toDto(Package pkg);


    @Mapping(target = "packageID", source = "packageID", ignore = true)
    @Mapping(target = "user", ignore = true)
    Package toPackage(PackageDto packageDto);

}
