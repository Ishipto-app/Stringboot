package com.example.apartmentmanager.service;

import com.example.apartmentmanager.entity.ApartmentRoom;
import com.example.apartmentmanager.repository.ApartmentRoomRepository;
import com.example.apartmentmanager.request.CreateApartmentRoomRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApartmentRoomService {
    @Autowired
    private final ApartmentRoomRepository apartmentRoomRepository;

    public List<ApartmentRoom> getAllRoom() {
        return apartmentRoomRepository.findAll();
    }

    public ApartmentRoom createApartmentRoom(CreateApartmentRoomRequest request) {
        ApartmentRoom apartmentRoom = new ApartmentRoom();
        apartmentRoom.setNumber(request.getNumber());
        apartmentRoom.setArea(request.getArea());
        apartmentRoom.setRoom(request.getRoom());
        return apartmentRoomRepository.save(apartmentRoom);
    }
}
