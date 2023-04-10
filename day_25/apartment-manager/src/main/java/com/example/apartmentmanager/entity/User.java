package com.example.apartmentmanager.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "id_card", unique = true)
    private String id_card;

    @Column(name = "sex")
    private Boolean sex;

    @Column(name = "deputy")
    private Boolean deputy;

    @ManyToOne
    @JoinColumn(name = "apartment_room_id")
    private ApartmentRoom apartmentRoom;

    @OneToMany(mappedBy = "user")
    private List<ApartmentTracking> apartmentTrackings;
}