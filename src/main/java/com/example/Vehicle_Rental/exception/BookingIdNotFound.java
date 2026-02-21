package com.example.Vehicle_Rental.exception;

public class BookingIdNotFound extends RuntimeException {
    public BookingIdNotFound(String message) {
        super(message);
    }
}
