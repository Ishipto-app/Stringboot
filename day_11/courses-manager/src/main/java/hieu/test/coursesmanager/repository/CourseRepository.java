package hieu.test.coursesmanager.repository;

import hieu.test.coursesmanager.dto.CourseDto;
import hieu.test.coursesmanager.exception.BadRequestException;
import hieu.test.coursesmanager.mapper.CourseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static hieu.test.coursesmanager.db.CourseDB.courses;

@Repository
public class CourseRepository {
    public List<CourseDto> getAllCourse(String type, String name, String topic) {
        return courses.stream()
                .filter(course -> ((type != null && !type.isEmpty() ? course.getType().equals(type) : true)
                        && (name != null && !name.isEmpty() ? course.getName().toLowerCase().contains(name.toLowerCase()) : true)
                        && (topic != null && !topic.isEmpty() ? course.getTopics().contains(topic) : true)))
                .map(course -> CourseMapper.courseDto(course))
                .toList();
    }

    public CourseDto getCourseById(int id) {
        Optional<CourseDto> courseOptional = courses.stream()
                .filter(course -> course.getId() == id)
                .map(course -> CourseMapper.courseDto(course))
                .findFirst();
        if(courseOptional.isPresent()){
            CourseDto courseDto = courseOptional.get();
            return courseDto;
        }
        throw new BadRequestException("không có course với id = " + id);
    }
}
