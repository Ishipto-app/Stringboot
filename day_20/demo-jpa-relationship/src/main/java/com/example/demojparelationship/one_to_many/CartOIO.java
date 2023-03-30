package com.example.demojparelationship.one_to_many;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CARTOIO")
public class CartOIO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    //bo mappedBy
    @JoinColumn(name = "cart_id") // we need to duplicate the physical information
    private List<ItemOIO> items;
}
