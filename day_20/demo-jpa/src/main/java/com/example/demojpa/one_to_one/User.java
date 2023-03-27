package com.example.demojpa.one_to_one;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    //one to one auto Eager / lazy
    //cascade anh huong tu con len cha
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "identity_card_id", referencedColumnName = "id")
    private IdentityCard identityCard;

    @PrePersist
    public void prePersist(){
        this.createAt = LocalDateTime.now();
    }

}