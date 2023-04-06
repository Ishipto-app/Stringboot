package com.example.apartmentmanager.repository;

import com.example.apartmentmanager.entity.ApartmentRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRoomRepository extends JpaRepository<ApartmentRoom, Long> {
}