package com.example.Vehicle_Rental.exception;

public class VehicleAlreadyBookedException extends RuntimeException {
    public VehicleAlreadyBookedException(String message) {
        super(message);
    }
}
