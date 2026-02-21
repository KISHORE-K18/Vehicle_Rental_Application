package com.example.Vehicle_Rental.service.bookingServices;

import com.example.Vehicle_Rental.dtos.BookingRequestDto;
import com.example.Vehicle_Rental.dtos.BookingResponseDto;
import com.example.Vehicle_Rental.exception.*;
import com.example.Vehicle_Rental.model.*;
import com.example.Vehicle_Rental.repository.BookingRepository;
import com.example.Vehicle_Rental.service.UserService;
import com.example.Vehicle_Rental.service.vehicleServices.vehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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


        if(vehicle.getVehicleInfo().getAvailabilityStatus()
                != BaseAvailabilityStatus.AVAILABLE) {
            throw new BookingException("Vehicle under maintenance");
        }

        boolean overlapExists =
                bookingRepository.existsByVehicleAndStatusInAndBookingDateTimeLessThanEqualAndReturnDateTimeGreaterThanEqual(
                        vehicle,
                        List.of(BookingStatus.CONFIRMED, BookingStatus.ON_RENT),
                        bookingRequestDto.getReturnDateTime(),
                        bookingRequestDto.getBookingDateTime()
                );

        if (overlapExists) {
            throw new VehicleAlreadyBookedException("Vehicle has already been booked");
        }

        Booking booking = new Booking();
        booking.setBookingDateTime(bookingRequestDto.getBookingDateTime());
        booking.setReturnDateTime(bookingRequestDto.getReturnDateTime());

        booking.setVehicle(vehicle);
        vehicle.getBookings().add(booking);

        booking.setUser(user);
        user.getBookings().add(booking);

        booking.setStatus(BookingStatus.CONFIRMED);

      return  bookingRepository.save(booking);

    }

    @Override
    public Page<Booking> getAllBookingsForUser(UUID userId, Pageable pageable) {
       return bookingRepository.findByUserId(userId, pageable);
    }

    @Override
    public void updateBookingToOnRent(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingIdNotFound("Booking Id not found"));

        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new BookingException("Only CONFIRMED bookings can be marked as OnRent");
        }

        // Update booking status
        booking.setStatus(BookingStatus.ON_RENT);
         bookingRepository.save(booking);
    }

    @Override
    public void updateBookingToOnCompleted(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingIdNotFound("Booking Id not found"));

        // Only ON_RENT bookings can be completed
        if (booking.getStatus() != BookingStatus.ON_RENT) {
            throw new BookingException("Only ON_RENT bookings can be completed");
        }

        // Update booking status
        booking.setStatus(BookingStatus.COMPLETED);
        bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(UUID bookingId, UUID userId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingIdNotFound("Booking not found"));

        // Ownership check
        if (!booking.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You cannot cancel this booking");
        }

        // Status validation
        if (booking.getStatus() == BookingStatus.ON_RENT) {
            throw new BookingException("Cannot cancel booking that is already ON_RENT");
        }

        if (booking.getStatus() == BookingStatus.COMPLETED) {
            throw new BookingException("Cannot cancel a completed booking");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BookingException("Booking already cancelled");
        }

        // Cancel booking
        booking.setStatus(BookingStatus.CANCELLED);

        bookingRepository.save(booking);
    }


    @Override
    public Optional<Booking> findBookingById(UUID bookingId) {
        return bookingRepository.findById(bookingId);
    }

    @Override
    public List<Booking> getBookingHistoryForVehicle(UUID vehicleId) {
        // Validate vehicle exists
        vehicleService.getVehicleById(vehicleId)
                .orElseThrow(() ->
                        new VehicleNotFoundException("Vehicle not found"));

        return bookingRepository.findByVehicleIdOrderByBookingDateTimeDesc(vehicleId);
    }



}


