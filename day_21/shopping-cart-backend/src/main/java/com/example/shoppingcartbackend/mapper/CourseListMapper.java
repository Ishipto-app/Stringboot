package com.example.shoppingcartbackend.mapper;

import com.example.shoppingcartbackend.dto.CourseListDto;
import com.example.shoppingcartbackend.entity.Course;
import org.springframework.data.domain.Page;

import java.util.List;

public class CourseListMapper {
    public static CourseListDto courseListDto(Integer page, Integer pageSize, Page<Course> courses){
        Integer totalItems = (int) courses.getTotalElements();
        Integer totalPages = courses.getTotalPages();
        List<Course> data = courses.getContent();
        return new CourseListDto(page, pageSize, totalPages, totalItems, data);
    }
}
