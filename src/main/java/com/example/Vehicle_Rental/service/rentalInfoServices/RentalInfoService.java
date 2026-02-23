package com.example.Vehicle_Rental.service.rentalInfoServices;

import com.example.Vehicle_Rental.model.RentalInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RentalInfoService {

    RentalInfo getRentalInfo(UUID bookingId);
    Page<RentalInfo> getAllRentalInfos(Pageable pageable);
}
