package hieu.test.coursesmanager.controller;

import hieu.test.coursesmanager.dto.CourseDto;
import hieu.test.coursesmanager.dto.CourseListDto;
import hieu.test.coursesmanager.request.CreateCourseRequest;
import hieu.test.coursesmanager.request.UpdateCourseRequest;
import hieu.test.coursesmanager.service.CourseAdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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

    // api/v1/admin/courses
    @PostMapping("courses")
    public CourseDto createCourse(@Valid @RequestBody CreateCourseRequest request){
        System.out.println(request.getName());
        return courseAdminService.createCourse(request);
    }

    // api/v1/admin/courses/{id}
    @PutMapping("courses/{id}")
    public CourseDto updateCourse(@PathVariable Integer id,@Valid @RequestBody UpdateCourseRequest request){
        return courseAdminService.updateCourse(id, request);
    }

    // api/v1/admin/courses/{id}
    @DeleteMapping("courses/{id}")
    public void deleteCourse(@PathVariable Integer id){
        courseAdminService.deleteCourse(id);
    }
}
