package com.example.demo.request;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetBmiByPostRequest {
    @NotEmpty(message = "Height khong duoc de trong")
    private String height;
    @NonNull()
    private Float weight;
}
