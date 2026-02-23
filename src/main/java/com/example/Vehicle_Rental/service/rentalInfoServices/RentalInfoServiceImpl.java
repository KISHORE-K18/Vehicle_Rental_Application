package com.example.Vehicle_Rental.service.rentalInfoServices;

import com.example.Vehicle_Rental.exception.BookingIdNotFound;
import com.example.Vehicle_Rental.model.RentalInfo;
import com.example.Vehicle_Rental.repository.RentalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalInfoServiceImpl implements RentalInfoService{

    private final RentalInfoRepository rentalInfoRepository;

    @Override
    public RentalInfo getRentalInfo(UUID bookingId) {
        return rentalInfoRepository.findByBooking_BookingID(bookingId).orElseThrow(
                ()->new BookingIdNotFound("Provided bookingId is not Found"));
    }

    @Override
    public Page<RentalInfo> getAllRentalInfos(Pageable pageable) {
        return rentalInfoRepository.findAll(pageable);
    }
}
