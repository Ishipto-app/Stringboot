package com.example.demojpa.responsitory;

import com.example.demojpa.one_to_many.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}