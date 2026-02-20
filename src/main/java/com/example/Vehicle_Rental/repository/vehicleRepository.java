package com.example.Vehicle_Rental.repository;

import com.example.Vehicle_Rental.dtos.GetVehicleDetailsResponseDto;
import com.example.Vehicle_Rental.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface vehicleRepository extends JpaRepository<Vehicle, UUID> {

    @Query("""
    SELECT v
    FROM Vehicle v
    LEFT JOIN FETCH v.bookings
    WHERE v.id = :vehicleId
""")
    Optional<Vehicle> findVehicleWithBookings(UUID vehicleId);
    Page<Vehicle> findAll(Pageable pageable);
}
