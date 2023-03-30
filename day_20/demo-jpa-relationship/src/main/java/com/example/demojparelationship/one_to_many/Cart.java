package com.example.demojparelationship.one_to_many;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    //many (Item) la ben so huu => mappedBy
    @OneToMany(mappedBy = "cart")
    private List<Item> items;

    @Column(name = "name")
    private String name;
}