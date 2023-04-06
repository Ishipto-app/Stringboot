package com.example.apartmentmanager.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "apartmentGeneraCost", orphanRemoval = true)
    private List<ApartmentRoom> apartmentRooms = new ArrayList<>();

}