package com.example.apartmentmanager.entity;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "apartment_tracking")
public class ApartmentTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time_in", nullable = false)
    private Timestamp time_in;

    @Column(name = "time_out")
    private String time_out;

    @ManyToOne
    @JoinColumn(name = "apartment_room_id")
    private ApartmentRoom apartmentRoom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}