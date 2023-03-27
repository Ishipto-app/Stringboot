package com.example.demojpa.one_to_many;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    //auto Lazy
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    //orphanRemoval tac dung len danh sach o duoi khi thay doi se thay doi truc tiep voi database
    private List<Blog> blogs = new ArrayList<>();

}