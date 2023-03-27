package com.example.demojpa;

import com.example.demojpa.many_to_many.Course;
import com.example.demojpa.many_to_many.Student;
import com.example.demojpa.responsitory.CourseRepository;
import com.example.demojpa.responsitory.StudentRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ManyToManyTests {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void save_student(){
        Faker faker = new Faker();
        for (int i = 0; i < 30; i++) {
            Student student = Student.builder()
                    .name(faker.name().fullName())
                    .build();
            studentRepository.save(student);

        }
    }
    @Test
    void save_course(){
        Faker faker = new Faker();
        Random rd = new Random();
        List<Student> students = studentRepository.findAll();
        for (int i = 0; i < 5; i++) {
            //random hoc sinh
            List<Student> rdList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                int rdIndex = rd.nextInt(students.size());
                Student std = students.get(rdIndex);
                if(!rdList.contains(std)){
                    rdList.add(std);
                }
            }
            Course course = Course.builder()
                    .name(faker.name().fullName())
                    .build();
            courseRepository.save(course);

        }
    }
}
