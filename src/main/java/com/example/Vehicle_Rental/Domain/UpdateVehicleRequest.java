package com.example.Vehicle_Rental.Domain;

import com.example.Vehicle_Rental.model.VehicleInfo;
import com.example.Vehicle_Rental.model.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleRequest {
    private UUID id;
    private String vehicleType;
    private VehicleStatus vehicleStatus;
    private VehicleInfo vehicleInfo;
}
