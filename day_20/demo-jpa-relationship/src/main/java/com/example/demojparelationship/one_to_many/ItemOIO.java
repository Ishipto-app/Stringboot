package com.example.demojparelationship.one_to_many;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ITEMSOIO")
public class ItemOIO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    // Cart la ben so huu ko khuyen khich => bo them moi/ sua doi
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private CartOIO cart;


}