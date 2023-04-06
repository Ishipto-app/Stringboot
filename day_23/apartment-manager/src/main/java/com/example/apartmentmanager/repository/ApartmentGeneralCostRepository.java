package com.example.apartmentmanager.repository;

import com.example.apartmentmanager.entity.ApartmentGeneralCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentGeneralCostRepository extends JpaRepository<ApartmentGeneralCost, Long> {
}