package com.example.coursemanagementjpa.service;

import com.example.coursemanagementjpa.dto.CourseDto;
import com.example.coursemanagementjpa.entity.Course;
import com.example.coursemanagementjpa.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseUserService {
    private final CourseRepository courseRepository;

    public List<Course> getAllCourse(String name, String type, String category) {
        return courseRepository.findCourseDemo1(name, type,category);
    }

    public List<CourseDto> getAllCourse1(String name, String type, String category) {
        return courseRepository.findCourseDemo(name, type,category);
    }
}
