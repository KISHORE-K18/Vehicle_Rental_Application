package com.example.Vehicle_Rental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="id",updatable = false,nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String vehicleType;

    @OneToOne(cascade = CascadeType.ALL)
    private VehicleInfo vehicleInfo;

    @OneToMany(mappedBy = "vehicle",cascade = CascadeType.PERSIST)
    private List<Booking> bookings;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;
}
