package com.example.Vehicle_Rental.repository;

import com.example.Vehicle_Rental.model.Booking;
import com.example.Vehicle_Rental.model.RentalInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RentalInfoRepository extends JpaRepository<RentalInfo, UUID> {
    Optional<RentalInfo> findByBooking(Booking booking);
    Optional<RentalInfo> findByBooking_BookingID(UUID bookingId);
    Page<RentalInfo> findAll( Pageable pageable);
}
