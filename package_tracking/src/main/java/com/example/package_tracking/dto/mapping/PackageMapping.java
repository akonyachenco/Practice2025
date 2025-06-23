package com.example.package_tracking.dto.mapping;

import com.example.package_tracking.dto.PackageDto;
import com.example.package_tracking.model.Package;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DeliveryMapping.class)
public interface PackageMapping {

    @Mapping(target = "packageID", source = "packageID")
    @Mapping(target = "userID", source = "user.userID")
    PackageDto toDto(Package pkg);


    @Mapping(target = "packageID", source = "packageID", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "deliveries", ignore = true)
    Package toEntity(PackageDto packageDto);

}
