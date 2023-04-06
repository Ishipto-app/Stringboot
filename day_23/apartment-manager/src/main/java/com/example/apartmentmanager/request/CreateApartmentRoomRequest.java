package com.example.apartmentmanager.request;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateApartmentRoomRequest {

    @NotNull(message = "number không được để trống")
    private Integer number;

    @NotNull(message = "area không được để trống")
    private Long area;

    private Long room;
}
