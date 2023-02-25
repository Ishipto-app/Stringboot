package hieu.test.coursesmanager.db;

import hieu.test.coursesmanager.model.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseDB {
//    public static List<String> topics = List.of("frontend", "backend", "database", "devops", "basic", "mobile");

    public static List<Course> courses = new ArrayList<>(List.of(
            new Course(1,"Khóa học 1","Mô tả","Online", List.of("backend", "java"),"online",3),
            new Course(2,"Khóa học 2","Mô tả","Offline", List.of("mobile", "database"),"online",1)
    ));
}