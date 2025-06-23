package com.example.package_tracking.dto.mapping;

import com.example.package_tracking.dto.DeliveryStatusDto;
import com.example.package_tracking.model.DeliveryStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryStatusMapping {

    @Mapping(target = "deliveryID", source = "delivery.deliveryID")
    DeliveryStatusDto toDto(DeliveryStatus deliveryStatus);

    @Mapping(target = "deliveryStatusID", ignore = true)
    @Mapping(target = "delivery", ignore = true)
    DeliveryStatus toEntity(DeliveryStatusDto deliveryStatusDto);
}
