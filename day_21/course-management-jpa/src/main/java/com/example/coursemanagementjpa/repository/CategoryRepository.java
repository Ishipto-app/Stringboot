package com.example.coursemanagementjpa.repository;

import com.example.coursemanagementjpa.entity.Category;
import com.example.coursemanagementjpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}