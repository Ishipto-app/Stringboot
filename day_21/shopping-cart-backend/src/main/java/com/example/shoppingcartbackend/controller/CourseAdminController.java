package com.example.shoppingcartbackend.controller;

import com.example.shoppingcartbackend.dto.CourseListDto;
import com.example.shoppingcartbackend.entity.Category;
import com.example.shoppingcartbackend.entity.Course;
import com.example.shoppingcartbackend.entity.User;
import com.example.shoppingcartbackend.request.CreateCourseRequest;
import com.example.shoppingcartbackend.request.UpdateCourseRequest;
import com.example.shoppingcartbackend.service.CourseAdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/admin")
@AllArgsConstructor
public class CourseAdminController {
    @Autowired
    private final CourseAdminService courseAdminService;

    //api/v1/admin/courses?page={pageValue}&pageSize={pageSizeValue}
    @GetMapping("courses")
    public CourseListDto getAllCourses(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        return courseAdminService.getAllCourseByPageAndPagesize(page, pageSize);
    }

    // api/v1/admin/courses/{id}
    @GetMapping("courses/{id}")
    public Course getCourseById(@PathVariable Integer id){
        return courseAdminService.getCourseById(id);
    }

    // api/v1/admin/courses
    @PostMapping("courses")
    public Course createCourse(@Valid @RequestBody CreateCourseRequest request){
        return courseAdminService.createCourse(request);
    }

    // api/v1/admin/courses/{id}
    @PutMapping("courses/{id}")
    public Course updateCourse(@PathVariable Integer id,@Valid @RequestBody UpdateCourseRequest request){
        return courseAdminService.updateCourse(id, request);
    }

    // api/v1/admin/courses/{id}
    @DeleteMapping("courses/{id}")
    public void deleteCourse(@PathVariable Integer id){
        courseAdminService.deleteCourse(id);
    }

    // api/v1/admin/category}
    @GetMapping("category")
    public List<Category> getAllCategories(){
        return courseAdminService.getAllCategories();
    }
    // api/v1/admin/user}
    @GetMapping("user")
    public List<User> getAllUsers(){
        return courseAdminService.getAllUsers();
    }
}
