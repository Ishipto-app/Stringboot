package com.example.apartmentmanager.entity;

import lombok.*;

import javax.persistence.*;
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

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "apartment_room_id")
    private List<User> users = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "apartment_room_id")
    private List<ApartmentCost> apartmentCosts = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "apartment_room_id")
    private List<ApartmentGeneralCost> apartmentGeneralCosts = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "apartment_room_apartment_trackings",
            joinColumns = @JoinColumn(name = "apartment_room_id"),
            inverseJoinColumns = @JoinColumn(name = "apartment_trackings_id"))
    private Set<ApartmentTracking> apartmentTrackings = new LinkedHashSet<>();

}