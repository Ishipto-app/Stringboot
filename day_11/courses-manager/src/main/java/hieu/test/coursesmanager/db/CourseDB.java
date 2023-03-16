package hieu.test.coursesmanager.db;

import hieu.test.coursesmanager.model.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseDB {
//    public static List<String> topics = List.of("frontend", "backend", "database", "devops", "basic", "mobile");

    public static List<Course> courses = new ArrayList<>(List.of(
            new Course(1,"Spring Boot - Web Back End","Mô tả","Trực tuyến", List.of("backend", "java"),"online",3),
            new Course(2,"Spring Boot - 123","Mô tả","Phòng Lab", List.of("mobile", "database"),"online",1),
            new Course(3,"Spring Boot - 456","Mô tả","Phòng Lab", List.of("mobile", "database"),"online",1),
            new Course(4,"Spring Boot - 121","Mô tả","Trực tuyến", List.of("mobile", "database"),"online",1),
            new Course(5,"Spring Boot - 467","Mô tả","Phòng Lab", List.of("mobile", "database"),"online",1),
            new Course(6,"Spring Boot - 789","Mô tả","Phòng Lab", List.of("mobile", "database"),"online",1)
    ));
}