package com.example.demojpa.responsitory;

import com.example.demojpa.many_to_many.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}