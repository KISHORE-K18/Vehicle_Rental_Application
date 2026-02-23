package com.example.Vehicle_Rental.controller;

import com.example.Vehicle_Rental.dtos.RentalInfoDto;
import com.example.Vehicle_Rental.mapper.BookingMapper;
import com.example.Vehicle_Rental.model.RentalInfo;
import com.example.Vehicle_Rental.service.rentalInfoServices.RentalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rentalInfo")
@RequiredArgsConstructor
public class RentalInfoController {

    private final RentalInfoService rentalInfoService;
    private final BookingMapper bookingMapper;

    @GetMapping("/{bookingId}")
    public ResponseEntity<RentalInfoDto> getRentalInfoForTheBookingId(@PathVariable UUID bookingId) {
        RentalInfo rentalInfo = rentalInfoService.getRentalInfo(bookingId);

        RentalInfoDto rentalInfoDto = bookingMapper.toRentalInfoDto(rentalInfo);
        return ResponseEntity.ok(rentalInfoDto);

    }

    @GetMapping("/all")
    public ResponseEntity<Page<RentalInfoDto>> getAllRentalInfos(
            Pageable pageable
    ) {

        Page<RentalInfo> rentalInfos =rentalInfoService.getAllRentalInfos(pageable);
        return ResponseEntity
                .ok(rentalInfos.map(bookingMapper::toRentalInfoDto));

    }

}
