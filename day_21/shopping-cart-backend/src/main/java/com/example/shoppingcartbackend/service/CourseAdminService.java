package com.example.shoppingcartbackend.service;

import com.example.shoppingcartbackend.dto.CourseListDto;
import com.example.shoppingcartbackend.entity.Category;
import com.example.shoppingcartbackend.entity.Course;
import com.example.shoppingcartbackend.entity.User;
import com.example.shoppingcartbackend.exception.NotFoundException;
import com.example.shoppingcartbackend.mapper.CourseListMapper;
import com.example.shoppingcartbackend.repository.CategoryRepository;
import com.example.shoppingcartbackend.repository.CourseAdminRepository;
import com.example.shoppingcartbackend.repository.UserRepository;
import com.example.shoppingcartbackend.request.CreateCourseRequest;
import com.example.shoppingcartbackend.request.UpdateCourseRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseAdminService {
    @Autowired
    private final CourseAdminRepository courseAdminRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public CourseListDto getAllCourseByPageAndPagesize(Integer page, Integer pageSize) {
        //kiem tra null param
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 10 : pageSize;
        Page<Course> courses = courseAdminRepository.findAll(PageRequest.of(page, pageSize));
        return CourseListMapper.courseListDto(page, pageSize, courses);
    }

    public Course createCourse(CreateCourseRequest request) {
        Optional<User> user = userRepository.findById(request.getUserId());
        List<Category> categories = categoryRepository.findAllById(request.getCategories());
        if(user.isPresent()) {
            Course course = Course.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .type(request.getType())
                    .thumbnail(request.getThumbnail())
                    .price(request.getPrice())
                    .rating(request.getRating())
                    .user(user.get())
                    .categories(categories)
                    .build();
            return courseAdminRepository.save(course);
        } else {
            throw new NotFoundException("Not found user with id = " + request.getUserId());
        }
    }

    public Course updateCourse(Integer id, UpdateCourseRequest request) {
        Optional<Course> course = courseAdminRepository.findById(id);
        Optional<User> user = userRepository.findById(request.getUserId());
        List<Category> categories = categoryRepository.findAllById(request.getCategories());
        if(user.isEmpty()) {
            throw new NotFoundException("Not found user with id = " + request.getUserId());
        }
        if(course.isEmpty()) {
            throw new NotFoundException("Not found course with id = " + id);
        }
        Course newCourse = course.get();
        newCourse.setName(request.getName());
        newCourse.setName(request.getName());
        newCourse.setDescription(request.getDescription());
        newCourse.setType(request.getType());
        newCourse.setThumbnail(request.getThumbnail());
        newCourse.setPrice(request.getPrice());
        newCourse.setRating(request.getRating());
        newCourse.setUser(user.get());
        newCourse.setCategories(categories);
        return courseAdminRepository.save(newCourse);
    }

    public void deleteCourse(Integer id) {
        courseAdminRepository.deleteById(id);
    }
}
