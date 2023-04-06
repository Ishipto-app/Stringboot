package com.example.apartmentmanager.entity;

import lombok.*;

import javax.persistence.*;
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

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "apartment_room_id")
    private ApartmentRoom apartmentRoom;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

}