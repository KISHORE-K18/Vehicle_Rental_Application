package com.example.Vehicle_Rental.service.vehicleServices;

import com.example.Vehicle_Rental.Domain.CreateVehicleRequest;
import com.example.Vehicle_Rental.dtos.CreateVehicleRequestDto;
import com.example.Vehicle_Rental.model.Vehicle;
import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


public interface vehicleService {

    Vehicle createVehicle(CreateVehicleRequest createVehicleRequest, UUID adminId);
    Optional<Vehicle> getVehicleById(UUID vehicleId);
    void deleteVehicle(UUID vehicleId);
    Page<Vehicle> getAllVehicles(Pageable pageable);
}
