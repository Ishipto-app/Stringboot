package com.example.apartmentmanager.repository;

import com.example.apartmentmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}