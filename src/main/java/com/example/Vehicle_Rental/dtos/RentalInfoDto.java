package com.example.Vehicle_Rental.dtos;

import com.example.Vehicle_Rental.model.Booking;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalInfoDto {
    private UUID id;
    private UUID bookingID;
    private LocalDateTime actualPickupTime;
    private LocalDateTime actualReturnTime;
}
