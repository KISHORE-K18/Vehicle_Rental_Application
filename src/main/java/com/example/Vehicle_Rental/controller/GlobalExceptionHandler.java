package com.example.Vehicle_Rental.controller;

import com.example.Vehicle_Rental.dtos.ErrorDto;
import com.example.Vehicle_Rental.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex) {
        log.error("Caught UserNotFoundException", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("User not found");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VehicleUpdateException.class)
    public ResponseEntity<ErrorDto> handleVehicleUpdateException(VehicleUpdateException ex) {
        log.error("Caught VehicleUpdateException", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Vehicle update failed");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ErrorDto> handleVehicleNotFoundException(VehicleNotFoundException ex) {
        log.error("Caught VehicleNotFoundException", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Vehicle not found");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingException.class)
    public ResponseEntity<ErrorDto> handleBookingException(BookingException ex) {
        log.error("Caught BookingException", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Booking failed");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VehicleAlreadyBookedException.class)
    public ResponseEntity<ErrorDto> handleVehicleAlreadyBookedException(VehicleAlreadyBookedException ex) {
        log.error("Caught VehicleAlreadyBookedException", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Vehicle already booked");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingIdNotFound.class)
    public ResponseEntity<ErrorDto> handleVehicleAlreadyBookedException(BookingIdNotFound ex) {
        log.error("Caught BookingIdNotFound", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("BookingId not found");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> handleAccessDeniedException(AccessDeniedException ex) {
        log.error("Caught AccessDeniedException", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Access denied");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
