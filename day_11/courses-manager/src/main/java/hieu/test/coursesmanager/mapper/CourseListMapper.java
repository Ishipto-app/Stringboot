package hieu.test.coursesmanager.mapper;

import hieu.test.coursesmanager.dto.CourseDto;
import hieu.test.coursesmanager.dto.CourseListDto;
import hieu.test.coursesmanager.dto.UserDto;
import hieu.test.coursesmanager.exception.BadRequestException;
import hieu.test.coursesmanager.model.Course;
import hieu.test.coursesmanager.model.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static hieu.test.coursesmanager.db.CourseDB.courses;

public class CourseListMapper {
    public static CourseListDto courseListDto(Integer page, Integer pageSize, List<CourseDto> data){
        Integer totalItems = courses.size();
        Integer totalPages = (int) totalItems / pageSize;
        return new CourseListDto(page, pageSize, totalPages, totalItems, data);
    }
}
