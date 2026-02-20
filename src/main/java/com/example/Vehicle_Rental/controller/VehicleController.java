package com.example.Vehicle_Rental.controller;

import com.example.Vehicle_Rental.Domain.CreateVehicleRequest;
import com.example.Vehicle_Rental.Domain.UpdateVehicleRequest;
import com.example.Vehicle_Rental.auth.UserPrincipal;
import com.example.Vehicle_Rental.dtos.*;
import com.example.Vehicle_Rental.mapper.VehicleMapper;
import com.example.Vehicle_Rental.model.Vehicle;
import com.example.Vehicle_Rental.repository.userRepository;
import com.example.Vehicle_Rental.service.vehicleServices.vehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

import static com.example.Vehicle_Rental.Util.utils.parseUserId;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final vehicleService service;
    private final VehicleMapper vehicleMapper;

    @PostMapping
    public ResponseEntity<CreateVehicleResponseDto> createVehicle(
            @Valid @RequestBody CreateVehicleRequestDto createVehicleRequestDto
    ,@AuthenticationPrincipal UserPrincipal userPrincipal) {

        CreateVehicleRequest createVehicleRequest=vehicleMapper.fromDto(createVehicleRequestDto);

        UUID adminId = parseUserId(userPrincipal);

        Vehicle vehicle = service.createVehicle( createVehicleRequest,adminId);
        CreateVehicleResponseDto responseDto = vehicleMapper.toDto(vehicle);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<GetVehicleDetailsResponseDto> getAllVehicles(
            @PathVariable UUID vehicleId) {
        return service.getVehicleById(vehicleId)
                .map(vehicleMapper::toGetDetailsDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
}

  @GetMapping("/list")
    public ResponseEntity<Page<GetVehicleDetailsResponseDto>> getAllVehicles(Pageable pageable) {
        Page<Vehicle> vehicles = service.getAllVehicles(pageable);

        return ResponseEntity.ok(
                vehicles.map(vehicleMapper::toGetDetailsDto)
        );
  }
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(
            @PathVariable UUID vehicleId
    )
    {
        service.deleteVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{vehicleId}")
    public ResponseEntity<UpdateVehicleResponseDto> updateVehicle(
            @PathVariable UUID vehicleId,
            @Valid @RequestBody UpdateVehicleRequestDto updateVehicleRequestDto
    )
    {
        UpdateVehicleRequest updateVehicleRequest=vehicleMapper.fromDto(updateVehicleRequestDto);
        Vehicle vehicle = service.updateVehicle(vehicleId, updateVehicleRequest);

        UpdateVehicleResponseDto responseDto = vehicleMapper.toUpdateDto(vehicle);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
