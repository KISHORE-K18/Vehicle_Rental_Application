package com.example.Vehicle_Rental.mapper;

import com.example.Vehicle_Rental.dtos.BookingResponseDto;
import com.example.Vehicle_Rental.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface BookingMapper {

    @Mapping(source = "vehicle.id", target = "vehicleId")
    BookingResponseDto toBookingResponseDto(Booking booking);
}
