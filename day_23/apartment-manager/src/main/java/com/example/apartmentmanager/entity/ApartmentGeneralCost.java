package com.example.apartmentmanager.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
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
@Table(name = "apartment_genera_cost")
public class ApartmentGeneralCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "total")
    private Long total;

    @ManyToMany
    @JoinTable(name = "apartment_genera_cost_apartment_rooms",
            joinColumns = @JoinColumn(name = "apartment_general_cost_id"),
            inverseJoinColumns = @JoinColumn(name = "apartment_rooms_id"))
    private Set<ApartmentRoom> apartmentRooms = new LinkedHashSet<>();

}