package com.example.Vehicle_Rental.service.bookingServices;

import com.example.Vehicle_Rental.dtos.BookingRequestDto;
import com.example.Vehicle_Rental.dtos.BookingResponseDto;
import com.example.Vehicle_Rental.exception.BookingException;
import com.example.Vehicle_Rental.exception.UserNotFoundException;
import com.example.Vehicle_Rental.exception.VehicleAlreadyBookedException;
import com.example.Vehicle_Rental.exception.VehicleNotFoundException;
import com.example.Vehicle_Rental.model.*;
import com.example.Vehicle_Rental.repository.BookingRepository;
import com.example.Vehicle_Rental.service.UserService;
import com.example.Vehicle_Rental.service.vehicleServices.vehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final vehicleService vehicleService;
    private final UserService userService;

    @Override
    public Booking createBooking(UUID userId,BookingRequestDto bookingRequestDto) {

        User user =userService.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("Provided User Id is not found"));

        UUID vehicleId= bookingRequestDto.getVehicleId();
        System.out.println("Booking request id is: " + vehicleId);

        if(vehicleId==null){
            throw new VehicleNotFoundException("Vehicle not found");
        }

        Vehicle vehicle = vehicleService.getVehicleById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException("Provided Vehicle Id is not found"));

        // Date validation
        if(bookingRequestDto.getBookingDateTime()
                .isAfter(bookingRequestDto.getReturnDateTime())) {
            throw new BookingException("Invalid booking date range");
        }

        // Vehicle availability validation
        if(vehicle.getVehicleStatus() != VehicleStatus.ACTIVE) {
            throw new BookingException("Vehicle not available for booking");
        }

        if(vehicle.getVehicleInfo().getAvailabilityStatus()
                != BaseAvailabilityStatus.AVAILABLE) {
            throw new BookingException("Vehicle under maintenance");
        }

        boolean hasActiveBooking =
                bookingRepository.existsByVehicleAndStatusInAndBookingDateTimeLessThanEqualAndReturnDateTimeGreaterThanEqual(
                        vehicle,
                        List.of(BookingStatus.CONFIRMED, BookingStatus.ON_RENT),
                        bookingRequestDto.getReturnDateTime(),
                        bookingRequestDto.getBookingDateTime()
                );

        if (hasActiveBooking) {
            throw new VehicleAlreadyBookedException("Vehicle already has an active booking");
        }

        Booking booking = new Booking();
        booking.setBookingDateTime(bookingRequestDto.getBookingDateTime());
        booking.setReturnDateTime(bookingRequestDto.getReturnDateTime());

        booking.setVehicle(vehicle);
        vehicle.getBookings().add(booking);

        booking.setUser(user);
        user.getBookings().add(booking);

        booking.setStatus(BookingStatus.CONFIRMED);

        vehicle.setVehicleStatus(VehicleStatus.BOOKED);

      return  bookingRepository.save(booking);

    }

    @Override
    public Page<Booking> getAllBookingsForUser(UUID userId, Pageable pageable) {
       return bookingRepository.findByUserId(userId, pageable);
    }

}
