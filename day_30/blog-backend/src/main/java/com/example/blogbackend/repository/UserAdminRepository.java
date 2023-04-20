package com.example.blogbackend.repository;

import com.example.blogbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAdminRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}