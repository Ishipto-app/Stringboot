package com.example.shoppingcartbackend.repository;

import com.example.shoppingcartbackend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(nativeQuery = true, value = "select c.* from course c\n" +
            "left join course_categories cc \n" +
            "on c.id = cc.course_id\n" +
            "left join category ct\n" +
            "on cc.categories_id = ct.id\n" +
            "where (:type is null or c.type = :type)\n" +
            "and (:name is null or upper(c.name) like upper(concat('%', :name, '%')))\n" +
            "and (:category is null or upper(ct.name) like upper(concat('%', :category, '%')))\n" +
            "group by c.id"
    )
    List<Course> findAllCourse(@Param("type") @Nullable String type, @Param("name") @Nullable String name, @Param("category") @Nullable String category);
//    @Query("select c " +
//        "from Course c, Category ct " +
//        "where (:name is null or upper(c.name) like upper(concat('%', :name, '%')))\n" +
//        "and (:type is null or c.type = :type) " +
//        "and (:category is null or upper(ct.name) like upper(concat('%', :category, '%')))")
//    List<Course> findAllCourse(@Param("type") @Nullable String type, @Param("name") @Nullable String name, @Param("category") @Nullable String category);
}
