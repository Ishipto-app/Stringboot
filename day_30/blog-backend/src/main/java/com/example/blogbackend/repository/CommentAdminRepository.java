package com.example.blogbackend.repository;

import com.example.blogbackend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentAdminRepository extends JpaRepository<Comment, Integer> {
}