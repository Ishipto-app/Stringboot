package com.example.coursemanagementjpa.repository;

import com.example.coursemanagementjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}