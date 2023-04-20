package com.example.blogbackend.repository;

import com.example.blogbackend.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogAdminRepository extends JpaRepository<Blog, Integer> {
    Page<Blog> findByUser_Email(String email, Pageable pageable);

    Page<Blog> findByContentContainsIgnoreCase(String content, Pageable pageable);

}