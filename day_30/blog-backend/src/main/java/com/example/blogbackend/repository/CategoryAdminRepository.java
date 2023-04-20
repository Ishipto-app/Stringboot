package com.example.blogbackend.repository;

import com.example.blogbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryAdminRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByNameIgnoreCase(String name);

}