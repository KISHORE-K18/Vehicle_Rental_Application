package com.example.Vehicle_Rental.controller;

import com.example.Vehicle_Rental.auth.UserPrincipal;
import com.example.Vehicle_Rental.dtos.BookingRequestDto;
import com.example.Vehicle_Rental.dtos.BookingResponseDto;
import com.example.Vehicle_Rental.mapper.BookingMapper;
import com.example.Vehicle_Rental.model.Booking;
import com.example.Vehicle_Rental.service.bookingServices.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.Vehicle_Rental.Util.utils.parseUserId;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

//    @GetMapping("/{}")
//    public ResponseEntity<Page<BookingResponseDto>> getAllBookings(
//            @PathVariable UUID userId, Pageable pageable) {
//
//    }

    @PostMapping("")
    public ResponseEntity<BookingResponseDto> createBooking(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody BookingRequestDto bookingRequestDto) {

        UUID userId = parseUserId(userPrincipal);

        Booking booking = bookingService.createBooking(userId, bookingRequestDto);
        BookingResponseDto responseDto = bookingMapper.toBookingResponseDto(booking);
        return ResponseEntity.ok(responseDto);
    }
}
