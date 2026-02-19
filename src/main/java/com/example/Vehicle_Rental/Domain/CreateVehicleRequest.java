package com.example.Vehicle_Rental.Domain;

import com.example.Vehicle_Rental.model.VehicleInfo;
import com.example.Vehicle_Rental.model.VehicleStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleRequest {

    private String vehicleType;
    private VehicleStatus vehicleStatus;
    private VehicleInfo vehicleInfo;
}
