package com.example.apartmentmanager.repository;

import com.example.apartmentmanager.entity.ApartmentCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentCostRepository extends JpaRepository<ApartmentCost, Long> {
}