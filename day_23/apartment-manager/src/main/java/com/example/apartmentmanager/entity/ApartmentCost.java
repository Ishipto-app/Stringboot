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
@Table(name = "apartment_cost")
public class ApartmentCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "unit")
    private String unit;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "price")
    private Long price;

    @Column(name = "pay_date")
    private Timestamp pay_date;

    @Column(name = "pay_status")
    private Boolean pay_status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "apartment_room_id")
    private ApartmentRoom apartmentRoom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}