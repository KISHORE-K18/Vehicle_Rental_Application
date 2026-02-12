package com.example.Vehicle_Rental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VehicleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "vehicleInfoId",updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String vehicleName;

    @Column(nullable = false)
    private int totalSeats;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BaseAvailabilityStatus availabilityStatus;


}
