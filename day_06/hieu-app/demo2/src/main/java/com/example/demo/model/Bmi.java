package com.example.demo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bmi {
    private String height;
    private Float weight;
    private Float bmi;
}
