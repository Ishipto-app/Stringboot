package com.example.shoppingcartbackend.entity;

import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private String type;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "price")
    private Integer price;
    @Column(name = "rating")
    private Double rating;

    //@JsonProperty("user_info") => tra ve client user => user_info
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //@JsonIgnore ko tra ve client categories
    @ManyToMany
    @JoinTable(name = "course_categories",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private List<Category> categories = new ArrayList<>();

}