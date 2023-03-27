package com.example.coursemanagementjpa.repository;

import com.example.coursemanagementjpa.dto.CourseDto;
import com.example.coursemanagementjpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(nativeQuery = true, name = "findUserDemo")
    List<CourseDto> findCourseDemo(@Param("name") String name,
                                   @Param("type") String type,
                                   @Param("category") String category);

    @Query("select c " +
            "from Course c, Category ct " +
            "where (:name is null or c.name = :name) " +
            "and (:type is null or c.type = :type) " +
            "and (:category is null or ct.name = :category)")
    List<Course> findCourseDemo1(@Param("name") String name,
                                @Param("type") String type,
                                @Param("category") String category);
}