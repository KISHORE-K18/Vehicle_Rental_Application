package com.example.Vehicle_Rental.repository;

import com.example.Vehicle_Rental.model.Booking;
import com.example.Vehicle_Rental.model.BookingStatus;
import com.example.Vehicle_Rental.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    Page<Booking> findByUserId(UUID userId, Pageable pageable);
    boolean existsByVehicleAndStatusInAndBookingDateTimeLessThanEqualAndReturnDateTimeGreaterThanEqual(
            Vehicle vehicle,
            List<BookingStatus> statuses,
            LocalDateTime end,
            LocalDateTime start
    );
    List<Booking> findByVehicleIdOrderByBookingDateTimeDesc(UUID vehicleId);
    Booking findByBookingIDAndUserId(UUID bookingId,UUID userId);
}
