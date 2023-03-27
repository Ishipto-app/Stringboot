package com.example.shoppingcartbackend.repository;

import com.example.shoppingcartbackend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseAdminRepository extends JpaRepository<Course, Integer> {
}