package com.example.shoppingcartbackend.controller;

import com.example.shoppingcartbackend.entity.Course;
import com.example.shoppingcartbackend.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class CourseController {
    @Autowired
    private final CourseService courseService;

    @GetMapping("courses")
    public List<Course> getAllCourses(@RequestParam(required = false) String type, @RequestParam(required = false) String name, @RequestParam(required = false) String topic){
        return courseService.getAllCourse(type, name, topic);
    }

    // /api/v1/courses/{id}
    @GetMapping("courses/{id}")
    public Course getCourseById(@PathVariable int id){
        return courseService.getCourseById(id);
    }

}
