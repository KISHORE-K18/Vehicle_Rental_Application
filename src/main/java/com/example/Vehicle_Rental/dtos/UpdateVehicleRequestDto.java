package com.example.Vehicle_Rental.dtos;

import com.example.Vehicle_Rental.model.BaseAvailabilityStatus;
import com.example.Vehicle_Rental.model.VehicleStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVehicleRequestDto {

    @NotNull(message = "vehicle Id is required")
    private UUID id;

    @NotBlank(message = "vehicle Type is required")
    private String vehicleType;

    @NotNull(message = "vehicle Status is required")
    private VehicleStatus vehicleStatus;

    @NotNull(message = "Vehicle Information is requied")
    @Valid
    private UpdateVehicleInfoRequestDto vehicleInfo;

}
