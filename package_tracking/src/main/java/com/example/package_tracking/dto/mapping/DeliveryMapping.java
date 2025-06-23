package com.example.package_tracking.dto.mapping;

import com.example.package_tracking.dto.DeliveryDto;
import com.example.package_tracking.dto.PackageDto;
import com.example.package_tracking.model.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DeliveryStatusMapping.class)
public interface DeliveryMapping {

    @Mapping(target = "deliveryID", source = "deliveryID")
    @Mapping(target = "packageID", source = "pkg.packageID")
    @Mapping(target = "courierServiceID", source = "courierService.courierServiceID")
    DeliveryDto toDto(Delivery delivery);

    @Mapping(target = "deliveryID", source = "deliveryID", ignore = true)
    @Mapping(target = "pkg", ignore = true)
    @Mapping(target = "courierService", ignore = true)
    Delivery toEntity(DeliveryDto deliveryDto);
}
