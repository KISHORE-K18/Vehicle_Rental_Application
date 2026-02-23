package com.example.Vehicle_Rental.mapper;

import com.example.Vehicle_Rental.dtos.BookingResponseDto;
import com.example.Vehicle_Rental.dtos.GetBookingDetailsDto;
import com.example.Vehicle_Rental.dtos.RentalInfoDto;
import com.example.Vehicle_Rental.model.Booking;
import com.example.Vehicle_Rental.model.RentalInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface BookingMapper {

    @Mapping(source = "vehicle.id", target = "vehicleId")
    BookingResponseDto toBookingResponseDto(Booking booking);

    @Mapping(source = "vehicle.id", target = "vehicleId")
    GetBookingDetailsDto toGetBookingDetailsDto(Booking booking);


    @Mapping(source = "booking.bookingID",target = "bookingID")
    RentalInfoDto toRentalInfoDto(RentalInfo rentalInfo);

//    @Mapping(source = "booking.id",target = "bookingId")
//    RentalInfoDto toRentalInfoDto(Booking booking);
}
