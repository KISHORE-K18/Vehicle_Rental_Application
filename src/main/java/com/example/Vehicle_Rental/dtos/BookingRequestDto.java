package com.example.Vehicle_Rental.dtos;

import com.example.Vehicle_Rental.model.BookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {

    @NotNull
    private UUID vehicleId;

    @NotNull(message = "Booking start date is required")
    private LocalDateTime bookingDateTime;

    @NotNull(message = "Booking return date is required")
    private LocalDateTime returnDateTime;


}
