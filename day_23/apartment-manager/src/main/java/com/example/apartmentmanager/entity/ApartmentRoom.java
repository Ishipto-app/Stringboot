package com.example.apartmentmanager.entity;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "apartment_general_cost_id")
    private ApartmentGeneralCost apartmentGeneraCost;

}