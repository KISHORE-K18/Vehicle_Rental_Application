package com.example.Vehicle_Rental.service.vehicleServices;

import com.example.Vehicle_Rental.Domain.CreateVehicleRequest;
import com.example.Vehicle_Rental.Domain.UpdateVehicleRequest;
import com.example.Vehicle_Rental.dtos.CreateVehicleRequestDto;
import com.example.Vehicle_Rental.exception.UserNotFoundException;
import com.example.Vehicle_Rental.exception.VehicleNotFoundException;
import com.example.Vehicle_Rental.exception.VehicleUpdateException;
import com.example.Vehicle_Rental.model.*;
import com.example.Vehicle_Rental.repository.userRepository;
import com.example.Vehicle_Rental.repository.vehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class vehicleServiceImpl implements vehicleService {

    private final userRepository userRepository;
    private final vehicleRepository vehicleRepository;

    @Override
    public Vehicle createVehicle(CreateVehicleRequest createVehicleRequest, UUID adminId) {
        User admin=userRepository.findById(adminId)
                .orElseThrow(()-> new UserNotFoundException(
                        String.format("User with Id %s not found", adminId)));

        Vehicle vehicleCreate=new Vehicle();

        //createVehicleInfo

        VehicleInfo vehicleInfoCreate=createVehicleRequest.getVehicleInfo();

        VehicleInfo vehicleInfo=new VehicleInfo();
        vehicleInfo.setVehicleName(vehicleInfoCreate.getVehicleName());
        vehicleInfo.setAvailabilityStatus(vehicleInfoCreate.getAvailabilityStatus());
        vehicleInfo.setPrice(vehicleInfoCreate.getPrice());
        vehicleInfo.setTotalSeats(vehicleInfoCreate.getTotalSeats());


        vehicleCreate.setVehicleType(createVehicleRequest.getVehicleType());
        vehicleCreate.setVehicleInfo(vehicleInfo);
        vehicleCreate.setVehicleStatus(createVehicleRequest.getVehicleStatus());

        Vehicle saved = vehicleRepository.save(vehicleCreate);
        System.out.println(saved.getVehicleInfo().getId());
        return saved;

    }

    @Override
    public Optional<Vehicle> getVehicleById(UUID vehicleId) {
        return vehicleRepository.findVehicleWithBookings(vehicleId);
    }

    @Override
    public void deleteVehicle(UUID vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public Page<Vehicle> getAllVehicles(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }

    @Override
    public Vehicle updateVehicle(UUID vehicleId, UpdateVehicleRequest vehicleRequest) {
        if(null == vehicleRequest.getId()) {
            throw new VehicleUpdateException("Vehicle ID cannot be null");
        }

        if(!vehicleId.equals(vehicleRequest.getId())) {
            throw new VehicleUpdateException("Cannot update the ID of an vehicle");
        }

        Vehicle existingVehicle = vehicleRepository
                .findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(
                        String.format("Event with ID '%s' does not exist", vehicleId))
                );

        existingVehicle.setVehicleType(vehicleRequest.getVehicleType());
        existingVehicle.setVehicleStatus(vehicleRequest.getVehicleStatus());
        VehicleInfo vehicleInfo = existingVehicle.getVehicleInfo();

        if(vehicleInfo == null) {
            throw new VehicleUpdateException("VehicleInfo not found for this vehicle");
        }

        vehicleInfo.setVehicleName(vehicleRequest.getVehicleInfo().getVehicleName());
        vehicleInfo.setTotalSeats(vehicleRequest.getVehicleInfo().getTotalSeats());
        vehicleInfo.setPrice(vehicleRequest.getVehicleInfo().getPrice());
        vehicleInfo.setAvailabilityStatus(vehicleRequest.getVehicleInfo().getAvailabilityStatus());
        existingVehicle.setVehicleInfo(vehicleInfo);

//        // 6️⃣ Business rule validation (optional but recommended)
//        if(existingVehicle.getVehicleStatus() == VehicleStatus.NOT_ACTIVE &&
//                vehicleInfo.getAvailabilityStatus() == ) {
//            throw new VehicleUpdateException(
//                    "Cannot mark vehicle under maintenance while it is on rent"
//            );
//        }

        // 7️⃣ Return managed entity (auto-persisted)
        vehicleRepository.save(existingVehicle);
        return existingVehicle;




    }


}
