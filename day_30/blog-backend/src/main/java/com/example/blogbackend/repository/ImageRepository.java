package com.example.blogbackend.repository;

import com.example.blogbackend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> findByUser_Id(Integer id);


}