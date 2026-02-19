package com.example.Vehicle_Rental.dtos;

import com.example.Vehicle_Rental.model.VehicleInfo;
import com.example.Vehicle_Rental.model.VehicleStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleRequestDto {
    @NotBlank(message = "vehicle Type is required")
    private String vehicleType;

    @NotNull(message = "vehicle Status is required")
    private VehicleStatus vehicleStatus;

    @NotNull(message = "Vehicle Information is requied")
    @Valid
    private CreateVehicleInfoRequestDto vehicleInfo;


}
