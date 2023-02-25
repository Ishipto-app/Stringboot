package hieu.test.coursesmanager.service;

import hieu.test.coursesmanager.dto.CourseDto;
import hieu.test.coursesmanager.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    @Autowired
    private final CourseRepository courseRepository;
    public List<CourseDto> getAllCourse(String type, String name, String topic) {
        return courseRepository.getAllCourse(type, name, topic);

    }

    public CourseDto getCourseById(int id) {
        return courseRepository.getCourseById(id);
    }
}
