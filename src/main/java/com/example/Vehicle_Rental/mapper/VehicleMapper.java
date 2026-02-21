package com.example.Vehicle_Rental.mapper;

import com.example.Vehicle_Rental.Domain.CreateVehicleRequest;
import com.example.Vehicle_Rental.Domain.UpdateVehicleRequest;
import com.example.Vehicle_Rental.dtos.*;
import com.example.Vehicle_Rental.model.Booking;
import com.example.Vehicle_Rental.model.Vehicle;
import com.example.Vehicle_Rental.model.VehicleInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface VehicleMapper {
    CreateVehicleRequest fromDto(CreateVehicleRequestDto createVehicleRequestDto);
    CreateVehicleResponseDto toDto(Vehicle vehicle);
    CreateVehicleInfoResponseDto toDto(VehicleInfo vehicleInfo);

    GetVehicleDetailsResponseDto toGetDetailsDto(Vehicle vehicle);
    VehicleBookingDto toDto(Booking booking);

    UpdateVehicleRequest fromDto(UpdateVehicleRequestDto updateVehicleRequestDto);
    UpdateVehicleResponseDto toUpdateDto(Vehicle vehicle);
    UpdateVehicleInfoResponseDto toUpdateDto(VehicleInfo vehicleInfo);


}
