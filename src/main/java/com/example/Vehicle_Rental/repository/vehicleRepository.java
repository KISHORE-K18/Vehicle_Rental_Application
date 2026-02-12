package com.example.Vehicle_Rental.repository;

import com.example.Vehicle_Rental.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface vehicleRepository extends JpaRepository<Vehicle, UUID> {

}
