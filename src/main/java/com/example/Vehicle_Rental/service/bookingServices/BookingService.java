package com.example.Vehicle_Rental.service.bookingServices;

import com.example.Vehicle_Rental.dtos.BookingRequestDto;
import com.example.Vehicle_Rental.dtos.BookingResponseDto;
import com.example.Vehicle_Rental.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BookingService {

    Booking createBooking(UUID userId,BookingRequestDto bookingRequestDto);
    Page<Booking> getAllBookingsForUser(UUID userId, Pageable pageable);
}
