package com.example.demojpa.responsitory;

import com.example.demojpa.one_to_many.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}