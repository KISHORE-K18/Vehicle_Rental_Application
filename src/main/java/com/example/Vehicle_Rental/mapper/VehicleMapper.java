package com.example.Vehicle_Rental.mapper;

import com.example.Vehicle_Rental.Domain.CreateVehicleRequest;
import com.example.Vehicle_Rental.dtos.CreateVehicleInfoResponseDto;
import com.example.Vehicle_Rental.dtos.CreateVehicleRequestDto;
import com.example.Vehicle_Rental.dtos.CreateVehicleResponseDto;
import com.example.Vehicle_Rental.dtos.GetVehicleDetailsResponseDto;
import com.example.Vehicle_Rental.model.Vehicle;
import com.example.Vehicle_Rental.model.VehicleInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface VehicleMapper {
    CreateVehicleRequest fromDto(CreateVehicleRequestDto createVehicleRequestDto);
    CreateVehicleResponseDto toDto(Vehicle vehicle);
    CreateVehicleInfoResponseDto toDto(VehicleInfo vehicleInfo);

    GetVehicleDetailsResponseDto toGetDetailsDto(Vehicle vehicle);
}
