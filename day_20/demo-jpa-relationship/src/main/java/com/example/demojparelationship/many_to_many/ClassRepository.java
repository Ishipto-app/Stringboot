package com.example.demojparelationship.many_to_many;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Classroom, Integer> {
}