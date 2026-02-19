package com.example.Vehicle_Rental.dtos;

import com.example.Vehicle_Rental.model.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetVehicleDetailsResponseDto {
    private UUID id;
    private String vehicleType;
    private CreateVehicleInfoResponseDto vehicleInfo;
    private VehicleStatus vehicleStatus;
}
