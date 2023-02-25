package hieu.test.coursesmanager.controller;

import hieu.test.coursesmanager.dto.CourseDto;
import hieu.test.coursesmanager.service.CourseService;
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

    // /api/v1/courses?type={typeValue}&name={nameValue}&topic={topicValue}
    @GetMapping("courses")
    public List<CourseDto> getAllCourses(@RequestParam(required = false) String type, @RequestParam(required = false) String name, @RequestParam(required = false) String topic){
        return courseService.getAllCourse(type, name, topic);
    }

    // /api/v1/courses/{id}
    @GetMapping("courses/{id}")
    public CourseDto getCourseById(@PathVariable int id){
        return courseService.getCourseById(id);
    }
}
