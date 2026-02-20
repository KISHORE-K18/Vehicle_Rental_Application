package com.example.Vehicle_Rental.dtos;

import com.example.Vehicle_Rental.model.BaseAvailabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVehicleInfoResponseDto {
    private UUID id;

    private String vehicleName;

    private Integer totalSeats;

    private BigDecimal price;

    private BaseAvailabilityStatus availabilityStatus;
}
