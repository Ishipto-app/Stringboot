package com.example.shoppingcartbackend.service;

import com.example.shoppingcartbackend.entity.Course;
import com.example.shoppingcartbackend.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {
    @Autowired
    private final CourseRepository courseRepository;
    public List<Course> getAllCourse(String type, String name, String category) {
        System.out.println(type);
        System.out.println(name);
        System.out.println(category);
        return courseRepository.findAllCourse(type, name, category);

    }

    public Course getCourseById(int id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.get();
    }
}
