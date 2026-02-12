package com.example.Vehicle_Rental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bookingID;

    @ManyToOne()
    @JoinColumn(name = "userId",nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "vehicleId",nullable = false)
    private Vehicle vehicle;

    @Column(nullable = false)
    private LocalDateTime bookingDateTime;

    @Column(nullable = false)
    private LocalDateTime returnDateTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

}
