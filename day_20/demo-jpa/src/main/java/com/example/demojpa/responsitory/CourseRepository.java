package com.example.demojpa.responsitory;

import com.example.demojpa.many_to_many.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}