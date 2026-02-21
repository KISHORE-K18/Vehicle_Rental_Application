package com.example.Vehicle_Rental.controller;

import com.example.Vehicle_Rental.auth.UserPrincipal;
import com.example.Vehicle_Rental.dtos.BookingRequestDto;
import com.example.Vehicle_Rental.dtos.BookingResponseDto;
import com.example.Vehicle_Rental.dtos.GetBookingDetailsDto;
import com.example.Vehicle_Rental.exception.BookingIdNotFound;
import com.example.Vehicle_Rental.mapper.BookingMapper;
import com.example.Vehicle_Rental.model.Booking;
import com.example.Vehicle_Rental.model.User;
import com.example.Vehicle_Rental.service.bookingServices.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("")
    public ResponseEntity<Page<GetBookingDetailsDto>> getBooking(@AuthenticationPrincipal UserPrincipal userPrincipal
    , Pageable pageable) {
        UUID userId = parseUserId(userPrincipal);
        Page<Booking> bookingLists=bookingService.getAllBookingsForUser(userId,pageable);

        return ResponseEntity.ok(
                bookingLists.map(bookingMapper::toGetBookingDetailsDto)
        );
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<GetBookingDetailsDto> getBookingDetails(@PathVariable UUID bookingId) {
        Booking booking =bookingService.findBookingById(bookingId)
                .orElseThrow(() -> new BookingIdNotFound("Booking Id not found"));
        GetBookingDetailsDto responseDto = bookingMapper.toGetBookingDetailsDto(booking);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<String> cancelBooking(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID bookingId
    ) {
        UUID userId = parseUserId(userPrincipal);
        bookingService.cancelBooking(bookingId, userId);
        return ResponseEntity.ok("Booking cancelled successfully");
    }


    @PutMapping("/{bookingId}/on-rent")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> markOnRent(@PathVariable UUID bookingId) {

        bookingService.updateBookingToOnRent(bookingId);
        return ResponseEntity.ok("Booking marked as ON_RENT");
    }

    @PutMapping("/{bookingId}/on-completed")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> markOnCompleted(@PathVariable UUID bookingId) {

        bookingService.updateBookingToOnCompleted(bookingId);
        return ResponseEntity.ok("Booking marked as Completed");
    }


}
