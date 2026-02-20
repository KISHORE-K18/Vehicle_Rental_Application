package com.example.Vehicle_Rental.dtos;

import com.example.Vehicle_Rental.model.BaseAvailabilityStatus;
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
public class UpdateVehicleInfoRequestDto {

    @NotNull(message = "vehicleInfo Id is required")
    private UUID id;

    @NotBlank(message = "vehicle Name is required")
    private String vehicleName;

    @NotNull(message = " is required")
    @Positive(message = "seat must be Greater than zero")
    private Integer totalSeats;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be Greater than zero")
    private BigDecimal price;

    @NotNull(message = "Status is required")
    private BaseAvailabilityStatus availabilityStatus;
}
