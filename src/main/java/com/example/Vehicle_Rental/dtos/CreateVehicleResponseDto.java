package com.example.Vehicle_Rental.dtos;

import com.example.Vehicle_Rental.model.Booking;
import com.example.Vehicle_Rental.model.VehicleInfo;
import com.example.Vehicle_Rental.model.VehicleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleResponseDto {
    private UUID id;
    private String vehicleType;
    private CreateVehicleInfoResponseDto vehicleInfo;
    private VehicleStatus vehicleStatus;
}
