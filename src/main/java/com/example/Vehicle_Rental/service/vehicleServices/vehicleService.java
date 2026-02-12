package com.example.Vehicle_Rental.service.vehicleServices;

import com.example.Vehicle_Rental.model.Vehicle;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

public interface vehicleService {

    public ResponseEntity<Vehicle> addVehicle(Vehicle vehicle);

}
