package com.example.apartmentmanager.controller;

import com.example.apartmentmanager.entity.ApartmentRoom;
import com.example.apartmentmanager.request.CreateApartmentRoomRequest;
import com.example.apartmentmanager.service.ApartmentRoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class ApartmentRoomController {

    @Autowired
    private final ApartmentRoomService apartmentRoomService;

    @GetMapping("apartment-room")
    public List<ApartmentRoom> getAllRoom(){
        return apartmentRoomService.getAllRoom();
    }

    //http://localhost:8080/api/v1/apartment-room
    @PostMapping("apartment-room")
    public ApartmentRoom createApartmentRoom(@Valid @RequestBody CreateApartmentRoomRequest request){
        return apartmentRoomService.createApartmentRoom(request);
    }
}
