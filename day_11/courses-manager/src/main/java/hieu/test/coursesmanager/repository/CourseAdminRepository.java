package hieu.test.coursesmanager.repository;

import hieu.test.coursesmanager.dto.CourseDto;
import hieu.test.coursesmanager.dto.CourseListDto;
import hieu.test.coursesmanager.exception.BadRequestException;
import hieu.test.coursesmanager.mapper.CourseListMapper;
import hieu.test.coursesmanager.mapper.CourseMapper;
import hieu.test.coursesmanager.request.CreateCourseRequest;
import hieu.test.coursesmanager.request.UpdateCourseRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static hieu.test.coursesmanager.db.CourseDB.courses;
import hieu.test.coursesmanager.model.Course;

@Repository
public class CourseAdminRepository {
    public CourseListDto getAllCourseByPageAndPagesize(Integer page, Integer pageSize) {
        List<CourseDto> courseDto = courses.stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .map(course -> CourseMapper.courseDto(course))
                .toList();
        return CourseListMapper.courseListDto(page, pageSize, courseDto);
    }

    public CourseDto createCourse(CreateCourseRequest request) {
        int maxId = courses.stream()
                .mapToInt(Course::getId)
                .max()
                .orElse(Integer.MIN_VALUE);
        Course course = Course.builder()
                .id(maxId + 1)
                .name(request.getName())
                .description(request.getDescription())
                .type(request.getType())
                .topics(request.getTopics())
                .thumbnail(request.getThumbnail())
                .userId(request.getUserId())
                .build();
        courses.add(course);
        return CourseMapper.courseDto(course);
    }

    public CourseDto updateCourse(Integer id, UpdateCourseRequest request) {
        Optional<Course> courseOptional = courses.stream()
                .filter(course -> Objects.equals(course.getId(), id))
                .findFirst();
        if(courseOptional.isPresent()){
            Course course = courseOptional.get();
            course.setName(request.getName());
            course.setDescription(request.getDescription());
            course.setType(request.getType());
            course.setTopics(request.getTopics());
            course.setThumbnail(request.getThumbnail());
            course.setUserId(request.getUserId());
            return CourseMapper.courseDto(course);
        }
        throw new BadRequestException("không có course với id = " + id);
    }

    public void deleteCourse(Integer id) {
        Optional<Course> courseOptional = courses.stream()
                .filter(course -> Objects.equals(course.getId(), id))
                .findFirst();
        if(courseOptional.isPresent()){
            courses.removeIf(course -> Objects.equals(course.getId(), id));
            System.out.println("delete success course id = " + id);
        } else {
            throw new BadRequestException("không có course với id = " + id);
        }
    }
}