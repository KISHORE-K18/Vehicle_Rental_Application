package com.example.Vehicle_Rental.service.vehicleServices;

import com.example.Vehicle_Rental.Domain.CreateVehicleRequest;
import com.example.Vehicle_Rental.dtos.CreateVehicleRequestDto;
import com.example.Vehicle_Rental.exception.UserNotFoundException;
import com.example.Vehicle_Rental.model.User;
import com.example.Vehicle_Rental.model.Vehicle;
import com.example.Vehicle_Rental.model.VehicleInfo;
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
        return vehicleRepository.findById(vehicleId);
    }

    @Override
    public void deleteVehicle(UUID vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public Page<Vehicle> getAllVehicles(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }


}
