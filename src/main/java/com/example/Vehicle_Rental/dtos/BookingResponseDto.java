package com.example.Vehicle_Rental.dtos;

import com.example.Vehicle_Rental.model.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDto {
    private UUID bookingID;
    private UUID vehicleId;
    private LocalDateTime bookingDateTime;
    private LocalDateTime returnDateTime;
    private BookingStatus status;

}
