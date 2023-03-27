package com.example.coursemanagementjpa.controller;

import com.example.coursemanagementjpa.dto.CourseDto;
import com.example.coursemanagementjpa.entity.Course;
import com.example.coursemanagementjpa.service.CourseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
@RequiredArgsConstructor
public class CouseUserController {
    private final CourseUserService courseUserService;
    @GetMapping("")
    public List<Course> getAllCourse(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String type,
                                     @RequestParam(required = false) String category) {
        return courseUserService.getAllCourse(name, type, category);
    }

    @GetMapping("example")
    public List<CourseDto> getAllCourse1(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String type,
                                         @RequestParam(required = false) String category) {
        return courseUserService.getAllCourse1(name, type, category);
    }
}
