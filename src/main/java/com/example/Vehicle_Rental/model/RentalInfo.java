package com.example.Vehicle_Rental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RentalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne()
    @JoinColumn(name = "bookingId", nullable = false)
    private Booking booking;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime actualPickupTime;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime actualReturnTime;
}
