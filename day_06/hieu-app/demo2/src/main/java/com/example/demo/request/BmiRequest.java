package com.example.demo.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BmiRequest {
    private Double weight;
    private Double height;
}
