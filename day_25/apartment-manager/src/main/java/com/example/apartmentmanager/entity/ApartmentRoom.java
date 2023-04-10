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
@Table(name = "apartment_room")
public class ApartmentRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "number", unique = true)
    private Integer number;

    @Column(name = "area")
    private Long area;

    @Column(name = "room")
    private Long room;

    @OneToMany(mappedBy = "apartmentRoom")
    private List<ApartmentCost> apartmentCosts;

    @ManyToMany
    @JoinTable(
            name = "apartment_room_general_cost",
            joinColumns = @JoinColumn(name = "apartment_room_id"),
            inverseJoinColumns = @JoinColumn(name = "apartment_general_cost_id")
    )
    private List<ApartmentGeneralCost> apartmentGeneralCosts;

    @OneToMany(mappedBy = "apartmentRoom")
    private List<ApartmentTracking> apartmentTrackings;
}