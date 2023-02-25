package hieu.test.coursesmanager.service;

import hieu.test.coursesmanager.dto.CourseDto;
import hieu.test.coursesmanager.dto.CourseListDto;
import hieu.test.coursesmanager.repository.CourseAdminRepository;
import hieu.test.coursesmanager.request.CreateCourseRequest;
import hieu.test.coursesmanager.request.UpdateCourseRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CourseAdminService {
    @Autowired
    private final CourseAdminRepository courseAdminRepository;

    public CourseListDto getAllCourseByPageAndPagesize(Integer page, Integer pageSize) {
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 10 : pageSize;
        return courseAdminRepository.getAllCourseByPageAndPagesize(page, pageSize);
    }

    public CourseDto createCourse(CreateCourseRequest request) {
        return courseAdminRepository.createCourse(request);
    }

    public CourseDto updateCourse(Integer id, UpdateCourseRequest request) {
        return courseAdminRepository.updateCourse(id, request);
    }

    public void deleteCourse(Integer id) {
        courseAdminRepository.deleteCourse(id);
    }
}
