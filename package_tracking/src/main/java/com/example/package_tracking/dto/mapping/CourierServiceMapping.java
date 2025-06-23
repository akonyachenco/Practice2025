package com.example.package_tracking.dto.mapping;

import com.example.package_tracking.dto.CourierServiceDto;
import com.example.package_tracking.model.CourierService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DeliveryMapping.class)
public interface CourierServiceMapping {
    @Mapping(target = "courierServiceID", source = "courierServiceID")
    CourierServiceDto toDto(CourierService courierService);

    @Mapping(target = "courierServiceID", source = "courierServiceID", ignore = true)
    @Mapping(target = "deliveries", ignore = true)
    CourierService toEntity(CourierServiceDto courierServiceDto);
}
